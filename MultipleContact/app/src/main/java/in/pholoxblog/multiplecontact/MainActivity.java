package in.pholoxblog.multiplecontact;

import android.Manifest;
import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.Context;
import android.content.OperationApplicationException;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {


    Button addcontact, delcontact;
    private int phno;
    String name;
    final String alphabet = "ABCDEFGIFGHIJKMNOPQRSTUVWXYZ";
    public static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 1;
    Button txt_count;
    ArrayList<ContentProviderOperation> contactList = new ArrayList<ContentProviderOperation>();
    public static final int REQUEST_WRITE_CONTACTS = 2909;
    //
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addcontact = (Button) findViewById(R.id.addcontact);
        delcontact = (Button) findViewById(R.id.delcontact);
        txt_count = (Button) findViewById(R.id.count);
        int contactPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS);
        List<String> listPermissionsNeeded = new ArrayList<>();


        if (contactPermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.READ_CONTACTS);
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), REQUEST_ID_MULTIPLE_PERMISSIONS);
        }
        Context context = getApplicationContext();
        Context ctx = getBaseContext();

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
            addcontact.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        for (int j = 0; j < 1; j++) {
                            for (int i = 0; i < 500; i++) {
                                Random rand = new Random();
                                phno = 10000000 + rand.nextInt(900000000);
                                StringBuilder stringBuilder = new StringBuilder();
                                while (stringBuilder.length() < 5) {
                                    int index = (int) (rand.nextFloat() * alphabet.length());
                                    stringBuilder.append(alphabet.charAt(index));
                                }

                                name = stringBuilder.toString();

                                contactList.add(ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI)
                                        .withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, null)
                                        .withValue(ContactsContract.RawContacts.ACCOUNT_NAME, null)
                                        .withValue(ContactsContract.RawContacts.AGGREGATION_MODE, ContactsContract.RawContacts.AGGREGATION_MODE_DISABLED)
                                        .build());
                                contactList.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                                        .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                                        .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
                                        .withValue(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME, name)
                                        .build());
                                contactList.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                                        .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                                        .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
                                        .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, phno)
                                        .build());
                                contactList.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                                        .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                                        .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE)
                                        .withValue(ContactsContract.CommonDataKinds.Email.DATA, "chandan@gmail.com")
                                        .withValue(ContactsContract.CommonDataKinds.Email.TYPE,  ContactsContract.CommonDataKinds.Email.TYPE_WORK)
                                        .build());
                                contactList.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                                        .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                                        .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Event.CONTENT_ITEM_TYPE)
                                        .withValue(ContactsContract.CommonDataKinds.Event.DATA, "05/08/2015")
                                        .withValue(ContactsContract.CommonDataKinds.Event.TYPE, ContactsContract.CommonDataKinds.Event.TYPE_BIRTHDAY)
                                        .build());
                                contactList.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                                        .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                                        .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Note.CONTENT_ITEM_TYPE)
                                        .withValue(ContactsContract.CommonDataKinds.Note.NOTE, "Hello i am chandan")
                                        .build());
                                contactList.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                                        .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                                        .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Im.CONTENT_ITEM_TYPE)
                                        .withValue(ContactsContract.CommonDataKinds.Im.DATA, "Chandan.bera@lykapp.com")
                                        .withValue(ContactsContract.CommonDataKinds.Im.TYPE, ContactsContract.CommonDataKinds.Im.PROTOCOL_GOOGLE_TALK)
                                        .build());
                                contactList.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                                        .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                                        .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Organization.CONTENT_ITEM_TYPE)
                                        .withValue(ContactsContract.CommonDataKinds.Organization.COMPANY, "LYK Inc")
                                        .withValue(ContactsContract.CommonDataKinds.Organization.TITLE, "Mantech Venture")
                                        .withValue(ContactsContract.CommonDataKinds.Organization.TYPE, ContactsContract.CommonDataKinds.Organization.COMPANY)
                                        .build());
                             
                                contactList.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                                        .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                                        .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredPostal.CONTENT_ITEM_TYPE)
                                        .withValue(ContactsContract.CommonDataKinds.StructuredPostal.FORMATTED_ADDRESS, "EC-146 EC Block Near EC Park 7000064")
                                        .withValue(ContactsContract.CommonDataKinds.StructuredPostal.TYPE, ContactsContract.CommonDataKinds.StructuredPostal.TYPE_HOME)
                                        .build());

                                Log.d("XXXXXXPOSITION--->i--" ,+ i+"");
                                Log.d("XXXXXXXPOSITION--->j--" ,+ j+"");
                                Log.d("XXXXXXXXXNAME" + i, name.toString());
                                Log.d("XXX--->" + i, phno + "");


                                try {
                                    if (contactList != null && name!=null) {
                                        Log.d("XXXXXXXXXLLL",name+"----"+contactList);
                                        getContentResolver().applyBatch(ContactsContract.AUTHORITY, contactList);

                                        Toast.makeText(MainActivity.this, "Contact is successfully added", Toast.LENGTH_SHORT).show();
                                    }

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }


                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            });
        } else {
            requestLocationPermission();
        }


        delcontact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentResolver cr = getContentResolver();
                Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
                while (cur.moveToNext()) {
                    try {
                        String lookupKey = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.LOOKUP_KEY));
                        Uri uri = Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_LOOKUP_URI, lookupKey);
                        Log.d("ZZZZZZZZ", "Data deleted" + i++);
                        cr.delete(uri, null, null);
                        Toast.makeText(MainActivity.this, "All Contact Delete ", Toast.LENGTH_SHORT).show();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }


            }

        });
        txt_count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor cursor = managedQuery(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);

                int count = cursor.getCount();
                Toast.makeText(MainActivity.this, "Total: " + count, Toast.LENGTH_LONG).show();

            }
        });


    }

    protected void requestLocationPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_CONTACTS)) {

            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.WRITE_CONTACTS
            }, REQUEST_WRITE_CONTACTS);
        } else {

            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.WRITE_CONTACTS
            }, REQUEST_WRITE_CONTACTS);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode) {
            case REQUEST_WRITE_CONTACTS: {

                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    addcontact.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            try {
                                if (contactList != null) {
                                    getContentResolver().applyBatch(ContactsContract.AUTHORITY, contactList);
                                    Toast.makeText(getBaseContext(), "Contact is successfully added", Toast.LENGTH_SHORT).show();
                                }
                            } catch (RemoteException e) {
                                e.printStackTrace();
                            } catch (OperationApplicationException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }

                return;
            }


        }
    }


}
