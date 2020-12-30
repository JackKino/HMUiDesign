package com.example.dtse.slice;

import com.example.dtse.ResourceTable;
import com.example.dtse.callbacks.VideoPlayerCallback;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.ComponentContainer;
import ohos.agp.components.DependentLayout;
import ohos.agp.components.surfaceprovider.SurfaceProvider;
import ohos.agp.graphics.Surface;
import ohos.agp.graphics.SurfaceOps;
import ohos.global.resource.RawFileDescriptor;
import ohos.media.common.Source;
import ohos.media.player.Player;

public class SurfaceProviderAbilitySlice extends AbilitySlice   {
    private static Player mPlayer;

    private SurfaceProvider mSurfaceProvider;

    private DependentLayout mLayout;

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_surface_provider);
    }


    @Override
    public void onForeground(Intent intent) {
        super.onForeground(intent);
    }


    @Override

    protected void onActive() {

        super.onActive();

        initSurfaceProvider();

    }


    private void initSurfaceProvider() {

        mLayout = (DependentLayout) findComponentById(ResourceTable.Id_video_player_dl);

        mPlayer = new Player(this);

        mSurfaceProvider = new SurfaceProvider(this);

        mSurfaceProvider.getSurfaceOps().get().addCallback(new VideoSurfaceCallback());

        mSurfaceProvider.pinToZTop(true);
        mSurfaceProvider.setWidth(ComponentContainer.LayoutConfig.MATCH_PARENT);
        mSurfaceProvider.setHeight(1000);
        mLayout.addComponent(mSurfaceProvider);


    }


    class VideoSurfaceCallback implements SurfaceOps.Callback {


        @Override

        public void surfaceCreated(SurfaceOps surfaceOps) {

            // Log.i("surfaceCreated() called.");

            if (mSurfaceProvider.getSurfaceOps().isPresent()) {

                Surface surface = mSurfaceProvider.getSurfaceOps().get().getSurface();

              //  playUrl(surface);
                playLocalFile(surface);

            }

        }


        @Override

        public void surfaceChanged(SurfaceOps surfaceOps, int i, int i1, int i2) {

            //  Log.i("surfaceChanged() called.");

        }


        @Override

        public void surfaceDestroyed(SurfaceOps surfaceOps) {

            // Log.i("surfaceDestroyed() called.");

        }

    }
    private void playLocalFile(Surface surface) {

        try {

            RawFileDescriptor filDescriptor = getResourceManager().getRawFileEntry("resources/rawfile/test.mp4").openRawFileDescriptor();

            Source source = new Source(filDescriptor.getFileDescriptor(),filDescriptor.getStartPosition(),filDescriptor.getFileSize());

            mPlayer.setSource(source);

            mPlayer.setVideoSurface(surface);

            mPlayer.setPlayerCallback(new VideoPlayerCallback());


            mPlayer.prepare();

            mSurfaceProvider.setTop(0);

            mPlayer.play();

        } catch (Exception e) {

         //   Log.e("playUrl Exception:" + e.getMessage());

        }

    }

    private void playUrl(Surface surface) {

        try {

            Source source = new Source("video url");

            mPlayer.setSource(source);

            mPlayer.setVideoSurface(surface);

            mPlayer.setPlayerCallback(new VideoPlayerCallback());

            mPlayer.prepare();

            mSurfaceProvider.setTop(0);

            mPlayer.play();

        } catch (Exception e) {

           // Log.e("playUrl Exception:" + e.getMessage());

        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mPlayer!=null){
            mPlayer.stop();
        }
        mSurfaceProvider.removeFromWindow();
    }
}
