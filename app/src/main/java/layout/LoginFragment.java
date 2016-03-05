package layout;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
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

import org.w3c.dom.Text;

/**
 * Created by JIL on 04/03/16.
 */
public class LoginFragment extends Fragment {

    TextView tvUsername;
    private EditText EtPassword;
    Button btnLogin;
    DAOHealthApp daoHealthApp;
    Users existingUser = new Users();
    public LoginFragment()
    {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_layout, container, false);
        daoHealthApp = new DAOHealthApp(this.getActivity());
        tvUsername = (TextView) view.findViewById(R.id.SUsername);
        EtPassword = (EditText) view.findViewById(R.id.SPassword);
        btnLogin = (Button) view.findViewById(R.id.btnLoginUser);
        existingUser = daoHealthApp.getExistingUsers();
        tvUsername.setText(existingUser.getUsername().toUpperCase());

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (existingUser.getPassword().equals(EtPassword.getText().toString())) {
                    getActivity().finish();
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    getActivity().startActivity(intent);
                } else {
                    Snackbar.make(v, "Incorrect Password!", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                }
            }
        });
        return view;
    }
}

