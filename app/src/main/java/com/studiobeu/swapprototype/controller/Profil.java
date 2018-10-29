package com.studiobeu.swapprototype.controller;

import android.content.Intent;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.EditText;

import com.studiobeu.swapprototype.R;
import com.studiobeu.swapprototype.model.MyAdapter;
import com.studiobeu.swapprototype.model.Parametre;
import com.studiobeu.swapprototype.model.Reseau;

public class ProfilActivity extends AppCompatActivity implements

        GestureDetector.OnGestureListener,
        GestureDetector.OnDoubleTapListener{

    // detecteur de gestes
    private GestureDetectorCompat mDetector;

    private EditText mEditText;
    private String data;
    MyAdapter mMyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        mDetector = new GestureDetectorCompat(this,this);
        mDetector.setOnDoubleTapListener(this);

        mEditText = (EditText) findViewById(R.id.editName);
        mMyAdapter = new MyAdapter();

        if(MainActivity.mProfil.getMyContact().getNom() != null){
            mEditText.setText(MainActivity.mProfil.getMyContact().getNom());
        }

        final RecyclerView rv = (RecyclerView) findViewById(R.id.list_item);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(mMyAdapter);

        initProfil();
    }

    public void initProfil(){
        mMyAdapter.add(new Reseau(Parametre.ID_FACEBOOK,Parametre.KEY_FACEBOOK));
        mMyAdapter.add(new Reseau(Parametre.ID_TELEPHONE,Parametre.KEY_TELEPHONE));
        mMyAdapter.add(new Reseau(Parametre.ID_MAIL,Parametre.KEY_MAIL));
        mMyAdapter.add(new Reseau(Parametre.ID_SNAP,Parametre.KEY_SNAP));
        mMyAdapter.add(new Reseau(Parametre.ID_LINKEDIN,Parametre.KEY_LINKEDIN));
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mEditText.getText().toString() != null) {
         MainActivity.mProfil.getMyContact().setNom(mEditText.getText().toString());
        }
    }

    /** =========================== Capteur de mouvements ========================================*/
    @Override
    public boolean onFling(MotionEvent event1, MotionEvent event2,
                           float velocityX, float velocityY) {
        //Exemple d'application du Fling
        String geste;
        if( Math.abs(velocityX) < Math.abs(velocityY) ){

            if(velocityY<0){
                finish();
            }
        }
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        if (this.mDetector.onTouchEvent(event)) {
            return true;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent event) {
        // Log.d(DEBUG_TAG,"onDown: " + event.toString());
        return true;
    }

    @Override
    public void onLongPress(MotionEvent event) {
    }

    @Override
    public boolean onScroll(MotionEvent event1, MotionEvent event2, float distanceX,
                            float distanceY) {
        return true;
    }

    @Override
    public void onShowPress(MotionEvent event) {
    }

    @Override
    public boolean onSingleTapUp(MotionEvent event) {
        return true;
    }

    @Override
    public boolean onDoubleTap(MotionEvent event) {
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent event) {
        return true;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent event) {
        return true;
    }
}
