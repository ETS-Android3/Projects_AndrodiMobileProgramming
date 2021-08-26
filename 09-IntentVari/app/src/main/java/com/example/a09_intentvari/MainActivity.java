package com.example.a09_intentvari;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.EditText;

import java.net.URI;

public class MainActivity extends Activity {

    private static final int REQUEST_CODE = 1;
    EditText editTextInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ottengo il riferimento all'EditText
        editTextInput = findViewById(R.id.InputText);
    }

    public void onClickCercaInRubrica(View v) {
        // Creiamo un intent per prelevare contatti dalla rubrica
        Intent i = new Intent(
                Intent.ACTION_PICK, // l'azione è quella di prelevare un item
                ContactsContract.Contacts.CONTENT_URI // URI della rubrica
        );
        // Lanciamo l'activity dei contatti della rubrica ed atteniamo il risultato
        startActivityForResult(i, REQUEST_CODE);
    }

    public void onClickVisualizzaMappa(View v) {
        String indirizzo = editTextInput.getText().toString();
        if (indirizzo != null) {
            indirizzo = indirizzo.replace(' ', '+');
            Intent geoIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q="+indirizzo));
            startActivity(geoIntent);
        }
    }

    public void visualizzaRistorantiClicked(View v) {
        Uri gmmIntentUri = Uri.parse("geo:37.7749,-122.4194?z=10&q=restaurants");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        if (mapIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(mapIntent);
        }
    }



    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    // questo metodo è invocato quando otteniamo un risultato da un'altra activity lanciata
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // controlliamo che il codice della richiesta si quello corretto e che si andata a buonf ine
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            ContentResolver cr = getContentResolver();
            Cursor cursor = cr.query(data.getData(), null, null, null);

            if (cursor != null && cursor.moveToFirst()) {
                @SuppressLint("Range") String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                String where = ContactsContract.Data.CONTACT_ID
                        + " = ? AND "
                        + ContactsContract.Data.MIMETYPE
                        + " = ?";
                String[] whereParameters = new String[]{id, ContactsContract.CommonDataKinds.StructuredPostal.CONTENT_ITEM_TYPE};
                Cursor addrCur = cr.query(ContactsContract.Data.CONTENT_URI, null, where, whereParameters, null);

                if (addrCur != null && addrCur.moveToFirst()) {
                    @SuppressLint("Range") String formattedAddress = addrCur.getString(addrCur.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.FORMATTED_ADDRESS));
                    editTextInput.setText(formattedAddress);
                    addrCur.close();
                }
                cursor.close();
            }
        }
    }
}