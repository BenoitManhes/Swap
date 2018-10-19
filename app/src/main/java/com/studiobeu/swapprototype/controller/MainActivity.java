package com.studiobeu.swapprototype.controller;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.studiobeu.swapprototype.model.Contact;
import com.studiobeu.swapprototype.model.Parametre;
import com.studiobeu.swapprototype.model.Profil;

import com.studiobeu.swapprototype.R;
import com.studiobeu.swapprototype.model.Reseau;

public class MainActivity extends AppCompatActivity implements

        GestureDetector.OnGestureListener,
        GestureDetector.OnDoubleTapListener{
    // detecteur de gestes
    private GestureDetectorCompat mDetector;

    public static Profil mProfil;
    private SharedPreferences mPreferences;

    public static final String KEY_PROFIL = "profil";
    public static final String SPLITER_DATA = "%%%";
    public static final String SPLITER_ELEMENT = ",,,";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        mDetector = new GestureDetectorCompat(this,this);
        mDetector.setOnDoubleTapListener(this);

        /*On creer le systeme de sauvegarde en prive*/
        mPreferences = getPreferences(MODE_PRIVATE);

        /*On charge le profil si il existe*/
        mProfil = new Profil();
        greetProfil();
    }

    /*methode pour chargerles donnees du profil*/
    private void greetProfil() {
        String dataProfilLoad = mPreferences.getString(KEY_PROFIL, null);//Lecture du dernier nom
        System.out.println("Data load"+dataProfilLoad);
        if (null != dataProfilLoad) {
            String[] dataProfilSplit = dataProfilLoad.split(SPLITER_DATA);

            for(String e: dataProfilSplit){
                String[] dataProfilElement = e.split(SPLITER_ELEMENT);
                Reseau r = new Reseau(-1,"");

                try {
                    switch (Integer.parseInt(dataProfilElement[0])) {

                        case Parametre.ID_NAME:
                            this.mProfil.getMyContact().setNom(dataProfilElement[1]);
                            break;
                        case Parametre.ID_FACEBOOK:
                            r = new Reseau(Parametre.ID_FACEBOOK, Parametre.KEY_FACEBOOK);
                            break;
                        case Parametre.ID_LINKEDIN:
                            r = new Reseau(Parametre.ID_LINKEDIN, Parametre.KEY_LINKEDIN);
                            break;
                        case Parametre.ID_SNAP:
                            r = new Reseau(Parametre.ID_SNAP, Parametre.KEY_SNAP);
                            break;
                        case Parametre.ID_MAIL:
                            r = new Reseau(Parametre.ID_MAIL, Parametre.KEY_MAIL);
                            break;
                        case Parametre.ID_TELEPHONE:
                            r = new Reseau(Parametre.ID_TELEPHONE, Parametre.KEY_TELEPHONE);
                            break;
                        default:
                            break;
                    }
                    if (r.getType() > 0) {
                        r.setName(dataProfilElement[1]);
                        r.setAdress(dataProfilElement[2]);
                        this.mProfil.getMyContact().add(r);
                    }
                }catch (Exception ex){

                }
            }
        }
    }

    public void saveProfil(){
        String data = ""+Parametre.ID_NAME+SPLITER_ELEMENT+mProfil.getMyContact().getNom();

        for (Reseau r : mProfil.getMyContact().getListReseau() ) {
            data += SPLITER_DATA+r.getType()+SPLITER_ELEMENT+r.getName()+SPLITER_ELEMENT+r.getAdress();
        }

        mPreferences.edit().putString(KEY_PROFIL,data).apply();
    }



    @Override
    protected void onStop() {
        super.onStop();
        saveProfil();
    }

    public void onClickQRcode(View view){
        Intent intent = new Intent(MainActivity.this, GenererQrCode.class);
        startActivity(intent);
    }

    public void onClickScan(View view){
        Intent intent = new Intent(MainActivity.this, QrScan.class);
        startActivity(intent);
    }

    public  void onClickProfil(View view){
        Intent intent = new Intent(MainActivity.this, ProfilActivity.class);
        startActivity(intent);
    }

    /** =========================== Capteur de mouvements ========================================*/
    @Override
    public boolean onFling(MotionEvent event1, MotionEvent event2,
                           float velocityX, float velocityY) {
        //Exemple d'application du Fling
        String geste;
        if( Math.abs(velocityX) > Math.abs(velocityY) ){
            if(velocityX>0) {
                geste = "Droite";
                Intent intent = new Intent(MainActivity.this, QrScan.class);
                startActivity(intent);

            }
            else {
                Intent intent = new Intent(MainActivity.this, GenererQrCode.class);
                startActivity(intent);
                geste = "Gauche";
            }
        }else{
            if(velocityY>0){
                geste = "Bas";
                Intent intent = new Intent(MainActivity.this, ProfilActivity.class);
                startActivity(intent);
            }
            else geste = "Haut";
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

    /** =================================== getter et setter =====================================*/

}
