package com.example.luxevistaresort.views.home;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.luxevistaresort.R;
import com.example.luxevistaresort.managers.UserManager;
import com.example.luxevistaresort.models.User;
import com.example.luxevistaresort.views.signin.LoginActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private TextView tvName, tvEmail, tvContact, tvNIC, tvGender, tvDOB;
    private ImageView ivProfileImage;
    private Button btnLogout;

    private UserManager userManager;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        userManager = new UserManager(requireContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        
        // Initialize views
        tvName = view.findViewById(R.id.tvName);
        tvEmail = view.findViewById(R.id.tvEmail);
        tvContact = view.findViewById(R.id.tvContact);
        tvNIC = view.findViewById(R.id.tvNIC);
        tvGender = view.findViewById(R.id.tvGender);
        tvDOB = view.findViewById(R.id.tvDOB);
        ivProfileImage = view.findViewById(R.id.ivProfileImage);
        btnLogout = view.findViewById(R.id.btnLogout);

        // Set up logout button
        btnLogout.setOnClickListener(v -> handleLogout());

        // Load user data
        loadUserData();

        return view;
    }

    private void loadUserData() {
        User currentUser = userManager.getCurrentUser();
        if (currentUser != null) {
            tvName.setText(currentUser.getName());
            tvEmail.setText(currentUser.getEmail());
            tvContact.setText(currentUser.getContact());
            tvNIC.setText(currentUser.getNic());
            tvGender.setText(currentUser.getGender());
            tvDOB.setText(currentUser.getDob());
            
            // Set profile image if exists
            byte[] profileImage = currentUser.getProfileImage();
            if (profileImage != null) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(profileImage, 0, profileImage.length);
                ivProfileImage.setImageBitmap(bitmap);
            }
        } else {
            // Set default values if no user data
            tvName.setText("Guest User");
            tvEmail.setText("Not available");
            tvContact.setText("Not available");
            tvNIC.setText("Not available");
            tvGender.setText("Not available");
            tvDOB.setText("Not available");
        }
    }

    private void handleLogout() {
        // Clear user data
        userManager.clearUser();

        // Navigate to login screen
        Intent intent = new Intent(requireActivity(), LoginActivity.class);
        // Clear back stack
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        requireActivity().finish();
    }
}