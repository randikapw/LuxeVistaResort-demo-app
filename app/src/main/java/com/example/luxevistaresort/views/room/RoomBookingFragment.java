package com.example.luxevistaresort.views.room;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.luxevistaresort.R;
import com.example.luxevistaresort.adapters.RoomAdapter;
import com.example.luxevistaresort.models.Room;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class RoomBookingFragment extends Fragment {

    private EditText editCheckIn, editCheckOut;
    private Spinner spinnerRoomType;
    private Button btnSearchRooms;
    private TextView btnSeeRoomDetails;
    private RecyclerView recyclerRooms;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_room_booking, container, false);

        // Initialize views
        editCheckIn = view.findViewById(R.id.editCheckIn);
        editCheckOut = view.findViewById(R.id.editCheckOut);
        spinnerRoomType = view.findViewById(R.id.spinnerRoomType);
        btnSearchRooms = view.findViewById(R.id.btnSearchRooms);
        btnSeeRoomDetails = view.findViewById(R.id.btnSeeRoomDetails);
        recyclerRooms = view.findViewById(R.id.recyclerRooms);

        // Setup date pickers
        editCheckIn.setOnClickListener(v -> showDatePicker(editCheckIn));
        editCheckOut.setOnClickListener(v -> showDatePicker(editCheckOut));

        // Setup room type spinner
        String[] roomTypes = {"Select Room Type", "All Room Types", "Standard Room", 
            "Deluxe Room", "Beachfront Suite", "Family Room", "Cabana"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(),
                android.R.layout.simple_spinner_dropdown_item, roomTypes);
        spinnerRoomType.setAdapter(adapter);

        // Setup search button
        btnSearchRooms.setOnClickListener(v -> searchAvailableRooms());

        // Setup see details button
        btnSeeRoomDetails.setOnClickListener(v -> {
            RoomsFragment roomsFragment = new RoomsFragment();
            
            FragmentTransaction transaction = requireActivity()
                .getSupportFragmentManager()
                .beginTransaction();

            transaction.replace(R.id.frameLayout, roomsFragment)
                .addToBackStack(null)
                .commit();
        });

        return view;
    }

    private void showDatePicker(EditText editText) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(requireContext(),
                (view, selectedYear, selectedMonth, selectedDay) -> {
                    String date = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
                    editText.setText(date);
                }, year, month, day);
        datePickerDialog.show();
    }

    private void searchAvailableRooms() {
        String checkIn = editCheckIn.getText().toString();
        String checkOut = editCheckOut.getText().toString();
        String selectedRoomType = spinnerRoomType.getSelectedItem().toString();

        if (checkIn.isEmpty() || checkOut.isEmpty() || selectedRoomType.equals("Select Room Type")) {
            Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // All available rooms data
        List<Room> allRooms = Arrays.asList(
            // Deluxe Rooms
            new Room(1, "Deluxe Room", "Luxurious room with modern amenities and stunning ocean views", 
                35000.00, 2, R.drawable.pic1, true, 
                "WiFi, AC, Mini Bar, Room Service, LED TV", "Ocean View", 1),
            new Room(2, "Deluxe Room", "Spacious room with garden views and private balcony", 
                32000.00, 2, R.drawable.pic1, true, 
                "WiFi, AC, Mini Bar, Room Service, LED TV", "Garden View", 1),
                
            // Beachfront Suites
            new Room(3, "Beachfront Suite", "Luxurious suite with direct beach access and panoramic views", 
                35800.00, 4, R.drawable.b1, true,
                "WiFi, AC, Mini Bar, Living Room, Private Balcony, Room Service", "Beach View", 2),
            new Room(4, "Beachfront Suite", "Premium suite with ocean views and upgraded amenities", 
                38000.00, 4, R.drawable.b1, true,
                "WiFi, AC, Mini Bar, Living Room, Private Balcony, Room Service, Jacuzzi", "Beach View", 2),
                
            // Cabanas
            new Room(5, "Cabana", "Private cabana with personalized service and pool access", 
                33000.00, 2, R.drawable.cabana1, true,
                "WiFi, AC, Private Pool, Room Service, Garden Access", "Garden View", 1),
            new Room(6, "Cabana", "Beachfront cabana with exclusive amenities", 
                36000.00, 2, R.drawable.cabana1, true,
                "WiFi, AC, Private Pool, Room Service, Direct Beach Access", "Beach View", 1),
                
            // Standard Rooms
            new Room(7, "Standard Room", "Comfortable room with essential amenities", 
                25000.00, 2, R.drawable.pic1, true,
                "WiFi, AC, Room Service", "Garden View", 1),
            new Room(8, "Standard Room", "Well-appointed room with partial ocean view", 
                28000.00, 2, R.drawable.pic1, true,
                "WiFi, AC, Room Service, LED TV", "Partial Ocean View", 1),
                
            // Family Rooms
            new Room(9, "Family Room", "Spacious room perfect for families with interconnected spaces", 
                45000.00, 6, R.drawable.b1, true,
                "WiFi, AC, Mini Bar, Living Room, Kids Play Area, Room Service", "Garden View", 3),
            new Room(10, "Family Room", "Premium family suite with ocean views", 
                48000.00, 6, R.drawable.b1, true,
                "WiFi, AC, Mini Bar, Living Room, Kids Play Area, Room Service, Game Console", "Ocean View", 3)
        );

        // Filter rooms based on selected type
        List<Room> filteredRooms;
        if (selectedRoomType.equals("All Room Types")) {
            filteredRooms = allRooms;
        } else {
            filteredRooms = allRooms.stream()
                .filter(room -> room.getType().equals(selectedRoomType))
                .collect(Collectors.toList());
        }

        // Sort rooms by price
        filteredRooms.sort((r1, r2) -> Double.compare(r1.getPrice(), r2.getPrice()));

        // Update spinner options to include all room types
        String[] roomTypes = {"Select Room Type", "All Room Types", "Standard Room", 
            "Deluxe Room", "Beachfront Suite", "Family Room", "Cabana"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(),
                android.R.layout.simple_spinner_dropdown_item, roomTypes);
        spinnerRoomType.setAdapter(adapter);

        // Setup RecyclerView
        recyclerRooms.setLayoutManager(new LinearLayoutManager(requireContext()));
        RoomAdapter roomAdapter = new RoomAdapter(filteredRooms, room -> {
            // Handle room booking
            String message = String.format(Locale.getDefault(),
                "Booking %s\nCheck-in: %s\nCheck-out: %s", 
                room.getType(), checkIn, checkOut);
            Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show();
            // TODO: Implement actual booking logic
        });
        recyclerRooms.setAdapter(roomAdapter);
        recyclerRooms.setVisibility(View.VISIBLE);

        // Show results summary
        String resultMessage = String.format(Locale.getDefault(),
            "Found %d available rooms", filteredRooms.size());
        Toast.makeText(requireContext(), resultMessage, Toast.LENGTH_SHORT).show();
    }
} 