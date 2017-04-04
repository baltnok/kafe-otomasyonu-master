package otomasyon.kafeotomasyonu;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import otomasyon.kafeotomasyonu.Modal.MenuGetirAdapter;
import otomasyon.kafeotomasyonu.Modal.SiparisEkleAdapter;
import otomasyon.kafeotomasyonu.Modal.SiparisGetir;
import otomasyon.kafeotomasyonu.Modal.Urunler;
import otomasyon.kafeotomasyonu.Modal.UrunlerListe;

public class SiparisEkle extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    ListView menu;
  //  final List<UrunlerListe> urunlerr=new ArrayList<UrunlerListe>();
    ArrayList<SiparisGetir> urunler = new ArrayList<SiparisGetir>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_siparis_ekle);
        //urunSayisi();
        siparisGoster();
        masaBilgileri();
        siparisVersetOnClick();
    }

    private void siparisVersetOnClick() {
    }

    private void menuListele() {

        SiparisEkleAdapter adapter = new SiparisEkleAdapter(this,R.layout.siparis_satir_layout,urunler);

        menu = (ListView)findViewById(R.id.lv_garson_menu);
        if(menu != null){
            menu.setAdapter(adapter);
        }
    }
    private void siparisGoster() {
        DatabaseReference myRef = database.getReference("urunler");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (int i=1;i<=Integer.parseInt(dataSnapshot.getChildrenCount()+"");i++)
                {
                    String urunadi = (String) dataSnapshot.child(String.valueOf(i)).child("urunadi").getValue();
                   // String fiyat = (String) String.valueOf(dataSnapshot.child(String.valueOf(i)).child("urunfiyati").getValue());
                    urunler.add(new SiparisGetir(urunadi,0));
    //                urunlerr.add(new UrunlerListe(urunadi,fiyat));
     //               butonOlustur(i,urunadi);
     //               Toast.makeText(SiparisEkle.this, urunadi, Toast.LENGTH_SHORT).show();
                }
                menuListele();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                //Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        });
    }
    private void masaBilgileri()
    {
        DatabaseReference myRef = database.getReference("siparisler");
        Bundle extras = getIntent().getExtras();
        int masaid = extras.getInt("masaNumarasi");
        TextView masa = (TextView) findViewById(R.id.textMasa);
        masa.setText("Masa "+String.valueOf(masaid));
    }
//    private void urunSayisi() {
//        String urunSayisi="0";
//        DatabaseReference myRef = database.getReference("urunler");
//        myRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                urunButonOlustur(Integer.parseInt(dataSnapshot.getChildrenCount()+""));
//
//            }
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                // Getting Post failed, log a message
//                //Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
//                // ...
//            }
//        });
//    }
//    void butonOlustur(int urunid,String urunAdi)
//    {
//            GridLayout gridLayout = (GridLayout)findViewById(R.id.urunler);
//            Button myButton = new Button(this);
//            myButton.setId(urunid);
//            myButton.setText(urunAdi);
//            //myButton.setTextAppearance(this,R.style.butonlar);
//            urunid--;
//            gridLayout.addView(myButton,urunid);
//            final int id=myButton.getId();
//            myButton = ((Button) findViewById(id));
//            myButton.setOnClickListener(new View.OnClickListener() {
//                public void onClick(View view) {  // butonun click olayi
//                    Toast.makeText(view.getContext(),
//                            "Urun "+ id +"'ya tiklandi", Toast.LENGTH_SHORT)
//                            .show();
//                    Button inputButton = (Button) findViewById(id);
//                    inputDialog(inputButton.getText().toString());
//
//                }
//            });
//    }
//    void urunButonOlustur(int gelen){
//        int satir=1,sutun=4;
//        GridLayout gridLayout = (GridLayout)findViewById(R.id.urunler);
//        if(gelen>5){
//            satir = (gelen/4)+1;
//        }
//        gridLayout.setColumnCount(sutun);
//        gridLayout.setRowCount(satir);
//    }
//    void inputDialog(String urunAdi)
//    {
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle(urunAdi);
//
//// Set up the input
//        final EditText input = new EditText(this);
//// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
//        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
//        builder.setView(input);
//
//// Set up the buttons
//        builder.setPositiveButton("Tamam", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                String m_Text = input.getText().toString();
//            }
//        });
//        builder.setNegativeButton("İptal", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.cancel();
//            }
//        });
//
//        builder.show();
//    }
}
