package com.example.dtse.callbacks;

import ohos.media.player.Player;

public class VideoPlayerCallback implements Player.IPlayerCallback  {
    @Override

    public void onPrepared() {

      //  Log.i("onPrepared");

    }



    @Override

    public void onMessage(int i, int i1) {

     //   Log.i("onMessage");

    }



    @Override

    public void onError(int i, int i1) {

       // Log.i("onError: i=" + i + ", i1=" + i1);

    }



    @Override

    public void onResolutionChanged(int i, int i1) {

       // Log.i("onResolutionChanged");

    }



    @Override

    public void onPlayBackComplete() {

      //  Log.i("onPlayBackComplete");

      /*  if (mPlayer != null) {

            mPlayer.stop();

            mPlayer = null;

        }*/

    }



    @Override

    public void onRewindToComplete() {

       // Log.i("onRewindToComplete");

    }



    @Override

    public void onBufferingChange(int i) {

      //  Log.i("onBufferingChange");

    }



    @Override

    public void onNewTimedMetaData(Player.MediaTimedMetaData mediaTimedMetaData) {

       // Log.i("onNewTimedMetaData");

    }



    @Override

    public void onMediaTimeIncontinuity(Player.MediaTimeInfo mediaTimeInfo) {

      //  Log.i("onMediaTimeIncontinuity");

    }
}
