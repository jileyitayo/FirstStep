package com.example.jil.firststep;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.DialogFragment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jil.Dialog.MyMoreInfoDialog;
import com.example.jil.SQLite.DAOChildApp;
import com.example.jil.SQLite.DAOHealthApp;
import com.example.jil.SQLite.DAOInfoMini;
import com.example.jil.SQLite.DAOMoreInformation;
import com.example.jil.SQLite.DAOVaccination;
import com.example.jil.Users.Child;
import com.example.jil.Users.ChildVaccination;
import com.example.jil.Users.MoreInformationModel;
import com.example.jil.Users.Users;
import com.example.jil.androidrecyclerviewgridview.ItemObject;

import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import layout.MainActivityFragment;
import me.drakeet.materialdialog.MaterialDialog;

public class AddChild_Activity extends AppCompatActivity {
    Child child = new Child();
    Button btnShowDate, btnMoreInfo, btnMoreInfoFull, btnVaccination;
    EditText etFirstName, etLastName, etWeight, etHeight, etAddLocation, etAllergies, etParents;
    ImageButton btnProfile;
    String etGender;

    int year_a, month_a, day_a;
    RadioGroup spinGender;
    public RadioButton radioSexButton;
    static final int DIALOG_ID = 0;
    DAOChildApp childApp;
    DAOHealthApp healthApp;
    DAOInfoMini mini;
    MoreInformationModel infoModel = new MoreInformationModel();
    DAOMoreInformation moreInformation;
    DAOVaccination daoVaccination;
    List<ItemObject> myList = new ArrayList<>();
    ItemObject object = new ItemObject();
    TextView tvDate;
    Users owner = new Users();
    private static int REQUEST_CAMERA = 1;
    private static int SELECT_FILE = 2;
    String uriPath, selectedRadioGender;
    //ArrayList<String> newData = new ArrayList<>();
    //SharedPreferences mSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_child_);
        Toolbar toolbarGreen = (Toolbar) findViewById(R.id.toolbarGreen);
        setSupportActionBar(toolbarGreen);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        childApp = new DAOChildApp(this);
        healthApp = new DAOHealthApp(this);
        daoVaccination = new DAOVaccination(this);
        moreInformation = new DAOMoreInformation(this);
        mini = new DAOInfoMini(this);
        owner = healthApp.getExistingUsers();
        spinGender = (RadioGroup) findViewById(R.id.radioSex);
        tvDate = (TextView) findViewById(R.id.lblDate);
        etFirstName = (EditText) findViewById(R.id.ETFName);
        etLastName = (EditText) findViewById(R.id.ETLName);
        etWeight = (EditText) findViewById(R.id.ETWeight);
        etHeight = (EditText) findViewById(R.id.ETHeight);
        etAddLocation = (EditText) findViewById(R.id.ETLocation);
        etAllergies = (EditText) findViewById(R.id.ETAllergies);
        etParents = (EditText) findViewById(R.id.ETParent);
        //lvVaccinations = (ListView) findViewById(R.id.listVIewVaccination);
        btnProfile = (ImageButton) findViewById(R.id.profilePic);
        btnProfile.setImageResource(R.drawable.ic_person_black_24dp);
        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                selectImage();

            }
        });
        //etWeight = (EditText) findViewById(R.id.ETWeight);
        final View view = View.inflate(this, R.layout.form_view, null);

        this.setTitle("New Child");
        FloatingActionButton btnSubmit = (FloatingActionButton) findViewById(R.id.btnSubmit);
        final Calendar cal = Calendar.getInstance();
        year_a = cal.get(Calendar.YEAR);
        month_a = cal.get(Calendar.MONTH);
        day_a = cal.get(Calendar.DAY_OF_MONTH);
        showDialogOnButtonClick();

        //btnMoreInfo = (Button) findViewById(R.id.btnMoreInfo);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(etFirstName.getText().toString().trim())) {
                    etFirstName.setError(getString(R.string.firstName_add));
                } else if (TextUtils.isEmpty(etLastName.getText().toString().trim())) {
                    etLastName.setError(getString(R.string.lastName_add));
                } else if (tvDate.getText().toString().isEmpty()) {
                    Snackbar.make(v, getString(R.string.dOB_add), Snackbar.LENGTH_LONG).setAction("Action", null).show();
                }
                else {
                    int selectedId = spinGender.getCheckedRadioButtonId();
                    radioSexButton = (RadioButton) findViewById(selectedId);
                    selectedRadioGender = radioSexButton.getText().toString();
                    Intent intent = new Intent(AddChild_Activity.this, Summary.class);

                    Bundle bundle = new Bundle();
                    bundle.putString("tFName", etFirstName.getText().toString().trim());
                    bundle.putString("tLName", etLastName.getText().toString().trim());
                    bundle.putString("tGender", selectedRadioGender);
                    bundle.putString("tDOB", tvDate.getText().toString());
                    bundle.putString("tHeight", etHeight.getText().toString().trim());
                    bundle.putString("tWeight", etWeight.getText().toString().trim());
                    bundle.putString("tlocation", etAddLocation.getText().toString().trim());
                    bundle.putString("tAllergies", etAllergies.getText().toString().trim());
                    bundle.putString("tParents", etParents.getText().toString().trim());
                    bundle.putString("uriPath", uriPath);
                    bundle.putStringArrayList("lists", Vaccinations.vaccines);

                    intent.putExtras(bundle);
                    startActivity(intent);
                    overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
                }

            }

        });

        btnVaccination = (Button) findViewById(R.id.vaccination);
        btnVaccination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddChild_Activity.this, Vaccinations.class));
            }
        });
        clearEtValues();
    }

    public void Submit1(View v)
    {


    }



    @Override
    protected void onDestroy() {
        mini.delete();
        super.onDestroy();
    }

    public void showDialogOnButtonClick() {
        btnShowDate = (Button) findViewById(R.id.btnShowDate);
        btnShowDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DIALOG_ID);
            }
        });
    }

    public void clearEtValues() {
        tvDate.setText("");
        etFirstName.setText("");
        etLastName.setText("");
        etAddLocation.setText("");
        etAllergies.setText("");
        etHeight.setText("");
        etParents.setText("");
        etWeight.setText("");
        //spinGender.setSelected(false);
    }


    private Child getChildFromLayout(long userid, String username, String firstName, String lastName, String gender,
                                     String DOB, String Imgpath, String height, String weight,
                                     String addLocation, String allergies, String parentNames) {
        Child child1 = new Child();
        child1.setFirstName(firstName);
        child1.setLastName(lastName);
        child1.setGender(gender);
        child1.setDateOfBirth(DOB);
        child1.setUserId(userid);
        child1.setUsername(username);
        child1.setWeight(weight);
        child1.setHeight(height);
        child1.setParentName(parentNames);
        child1.setAddLocation(addLocation);
        child1.setImg_path(Imgpath);
        child1.setVaccinationTaken(getDateTime());
        child1.setVaccination_due(null);
        child1.setAllergies(allergies);
        return child1;
    }
    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }


    /*
        private ChildVaccination getVaccinations(ArrayList<String> vaccinations )
        {
            ChildVaccination vaccination = new ChildVaccination();

        }
    */
    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == DIALOG_ID)
            return new DatePickerDialog(this, dpickerListener, year_a, month_a, day_a);
        return null;
    }


    private DatePickerDialog.OnDateSetListener dpickerListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            year_a = year;
            month_a = monthOfYear + 1;
            day_a = dayOfMonth;
            String dateSet = day_a + "/" + month_a + "/" + year_a;
            tvDate.setText(dateSet);
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.profile) {
            startActivity(new Intent(AddChild_Activity.this, Profile_Activity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private String[] getChildInfoTitle() {
        moreInformation = new DAOMoreInformation(this);
        List<MoreInformationModel> alist = moreInformation.getAllChildInfo();
        String[] list = new String[alist.size()];

        for (int i = 0; i < alist.size(); i++) {
            String title = alist.get(i).getInfo_title();
            list[i] = title;
        }
        moreInformation.close();
        return list;
    }

    private String[] getChildInfoDetails() {
        moreInformation = new DAOMoreInformation(this);
        List<MoreInformationModel> alist = moreInformation.getAllChildInfo();
        String[] list = new String[alist.size()];

        for (int i = 0; i < alist.size(); i++) {
            String details = alist.get(i).getInfo_details();
            list[i] = details;
        }
        moreInformation.close();
        return list;
    }


    public void showNoticeDialog() {
        // Create an instance of the dialog fragment and show it
        DialogFragment dialog = new MyMoreInfoDialog();
        dialog.show(getSupportFragmentManager(), "NoticeDialogFragment");
    }

    public void showInfoDialog(android.app.DialogFragment dialog1) {
        // Create an instance of the dialog fragment and show it
        //android.app.DialogFragment dialog1 = new AddedInfoList();
        dialog1.show(getFragmentManager(), "D_DialogFragment");
    }

    private void selectImage() {
        final CharSequence[] items = {"Take Photo", "Choose from Library", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(AddChild_Activity.this);
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("Take Photo")) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, REQUEST_CAMERA);
                } else if (items[item].equals("Choose from Library")) {
                    Intent intent = new Intent(
                            Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    startActivityForResult(
                            Intent.createChooser(intent, "Select File"),
                            SELECT_FILE);
                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CAMERA) {
                Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                thumbnail.compress(Bitmap.CompressFormat.JPEG, 200, bytes);//90
                String path=Environment.getExternalStorageDirectory().getPath() + "/DCIM/Camera/";
                String fileName =  String.format(System.currentTimeMillis()+".jpg");
                //Uri tempUri = getImageUri(getApplicationContext(), thumbnail);
                //File destination = new File(Environment.getExternalStorageDirectory(),System.currentTimeMillis() + ".jpg");

                File destination = new File(path, fileName);
                FileOutputStream fo;
                try {
                    destination.createNewFile();
                    fo = new FileOutputStream(destination);
                    fo.write(bytes.toByteArray());
                    //uriPath = destination.getAbsolutePath();
                    fo.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                uriPath = destination.getPath();
                btnProfile.setImageBitmap(thumbnail);
            } else if (requestCode == SELECT_FILE) {
                Uri selectedImageUri = data.getData();
                String[] projection = {MediaStore.MediaColumns.DATA};
                CursorLoader cursorLoader = new CursorLoader(this, selectedImageUri, projection, null, null,
                        null);
                Cursor cursor = cursorLoader.loadInBackground();
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
                cursor.moveToFirst();
                String selectedImagePath = cursor.getString(column_index);
                Bitmap bm;
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeFile(selectedImagePath, options);
                final int REQUIRED_SIZE = 200;
                int scale = 1;
                while (options.outWidth / scale / 2 >= REQUIRED_SIZE
                        && options.outHeight / scale / 2 >= REQUIRED_SIZE)
                    scale *= 2;
                options.inSampleSize = scale;
                options.inJustDecodeBounds = false;
                bm = BitmapFactory.decodeFile(selectedImagePath, options);
                btnProfile.setImageBitmap(bm);
                uriPath = selectedImagePath;
            }
        }
    }
    public String getRealPathFromURI(Uri uri) {
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        return cursor.getString(idx);
    }

    public static class MobileArrayAdapter extends ArrayAdapter<String> {
        private final Context context;
        private ArrayList<String> vaccinations;

        public MobileArrayAdapter(Context context, ArrayList<String> vaccinations) {
            super(context, R.layout.card_list_view2, vaccinations);
            this.context = context;
            this.vaccinations = vaccinations;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View rowView = inflater.inflate(R.layout.card_list_view2, parent, false);
            TextView textView = (TextView) rowView.findViewById(R.id.child_name);
            textView.setText(vaccinations.get(position));

            // Change icon based on name
            String s = vaccinations.get(position);
            return rowView;
        }
    }
}
