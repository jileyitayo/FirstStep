package layout;

import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.jil.SQLite.DAOHealthApp;
import com.example.jil.Users.Users;
import com.example.jil.firststep.MainActivity;
import com.example.jil.firststep.R;

/**
 * Created by JIL on 04/03/16.
 */
public class LoginFragment extends Fragment {

    TextView tvUsername;
    private EditText EtPassword;
    Button btnLogin;
    DAOHealthApp daoHealthApp;
    Users existingUser = new Users();
    SharedPreferences pref;
    public LoginFragment()
    {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_layout, container, false);
        daoHealthApp = new DAOHealthApp(this.getActivity());
        pref = getActivity().getSharedPreferences("loginRole", 0);
        tvUsername = (TextView) view.findViewById(R.id.SUsername);
        EtPassword = (EditText) view.findViewById(R.id.SPassword);
        btnLogin = (Button) view.findViewById(R.id.btnLoginUser);
        existingUser = daoHealthApp.getExistingUsers();
        tvUsername.setText(existingUser.getUsername().toUpperCase());

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final View view = v;
                final String etPassword1 = EtPassword.getText().toString();
                new AsyncTask<Void, Void, Void>()
                {
                    @Override
                    protected Void doInBackground(Void... params) {
                        if (existingUser.getPassword().equals(etPassword1)) {
                            SharedPreferences.Editor editor = pref.edit();
                            editor.putString("username", existingUser.getUsername());
                            editor.putString("role", existingUser.getRole());
                            editor.putString("email", existingUser.getEmailAddress());
                            editor.putString(",", existingUser.getPassword());
                            editor.apply();
                            getActivity().finish();
                            Intent intent = new Intent(getActivity(), MainActivity.class);
                            getActivity().startActivity(intent);

                        } else {
                            Snackbar.make(view, "Incorrect Password!", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                        }
                        return null;
                    }
                }.execute(null,null,null);

            }
        });
        return view;
    }
}

