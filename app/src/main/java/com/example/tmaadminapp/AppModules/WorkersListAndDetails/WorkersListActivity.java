package com.example.tmaadminapp.AppModules.WorkersListAndDetails;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tmaadminapp.Models.AddWorkerPresenterImplementer;
import com.example.tmaadminapp.Presenters.AddWorkerPresenter;
import com.example.tmaadminapp.R;
import com.example.tmaadminapp.Views.AddWorkerView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class WorkersListActivity extends AppCompatActivity implements AddWorkerView
{

    private Toolbar mToolbar;
    private RecyclerView workerListRecycler;
    private AdapterForWorkerList adapter;
    private LinearLayoutManager layoutManager;
    private AddWorkerPresenter presenter;
    private ProgressDialog progressDialog;
    private DatabaseReference dbRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.workers_list_activity);

        initViews();

        presenter.getAllWorkers(dbRef);

    }

    private void initViews()
    {
         presenter = new AddWorkerPresenterImplementer(this);

        //recycler view
        workerListRecycler = findViewById(R.id.sanitationWorkerList);
        layoutManager = new LinearLayoutManager(this);
        workerListRecycler.setLayoutManager(layoutManager);

        // toolbar
        mToolbar = findViewById(R.id.sanitation_worker_list_tool_bar);
        setSupportActionBar(mToolbar);
        setTitle("Worker List");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        progressDialog = new ProgressDialog(this , R.style.MyAlertDialogStyle);

        dbRef = FirebaseDatabase.getInstance().getReference().child("Worker List");

    }

    public void fabAddWorkerClick(View view)
    {
       presenter.fabClick();
    }

    @Override
    public void showProgressBar()
    {
      progressDialog.setMessage("Registering worker");
      progressDialog.setCanceledOnTouchOutside(false);
      progressDialog.show();
    }

    @Override
    public void hideProgressBar()
    {
       progressDialog.dismiss();
    }

    @Override
    public void showMessage(String message)
    {

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void clickOnAddWorkerFab()
    {
       addWorkerDialogForm();
    }

    @Override
    public void getWorkersList(List<ModelForWorkerList> workerList)
    {
          adapter = new AdapterForWorkerList(workerList , this);
          workerListRecycler.setAdapter(adapter);
    }

    private void addWorkerDialogForm()
    {
        View customView =  LayoutInflater.from(this).inflate(R.layout.add_worker_dialog_layout, null);
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setView(customView);

        final AlertDialog dialog = alert.create();
        dialog.setCanceledOnTouchOutside(false);

        // initialize editext views
        final EditText workerName , workerPhoneNo, workerCnic;
        workerName  = customView.findViewById(R.id.worker_name);
        workerPhoneNo  = customView.findViewById(R.id.worker_phone_no);
        workerCnic  = customView.findViewById(R.id.worker_cnic);

        // click on add worker button
        customView.findViewById(R.id.add_worker_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                presenter.addWorker(dbRef ,workerName.getText().toString() ,
                        workerPhoneNo.getText().toString(),
                        workerCnic.getText().toString());

                dialog.dismiss();
            }
        });


        dialog.show();

    }
}




/**
 *
 *  // dummy method to get the rating of user
 *     private void getCurrentCompleintRating()
 *     {
 *
 *         DatabaseReference db  = FirebaseDatabase.getInstance().getReference().child("Rating").child("-M5IfsswNw71ryqIdqFW");
 *
 *         db.addValueEventListener(new ValueEventListener() {
 *             @Override
 *             public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
 *
 *                 String rating = dataSnapshot.child("user_rating").getValue().toString();
 *                 Log.d("ComplaintRating", "onDataChange: "+rating);
 *             }
 *
 *             @Override
 *             public void onCancelled(@NonNull DatabaseError databaseError) {
 *
 *             }
 *         });
 *
 *
 *     }
 *     private void getRating()
 *     {
 *        final Query query =   dbRef.orderByChild("name").equalTo("sgdgf");
 *
 *               query.addChildEventListener(new ChildEventListener() {
 *                   @Override
 *                   public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s)
 *                   {
 *                       key =  dataSnapshot.child("pushKey").getValue().toString();
 *
 *                       DatabaseReference db  = FirebaseDatabase.getInstance().getReference().child("Rating");//.child(key);
 *                       Query query1 =  db.orderByChild("worker_id").equalTo(key);
 *
 *                       query1.addValueEventListener(new ValueEventListener() {
 *                           @Override
 *                           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
 *
 *                               String name= null;
 *                               for(DataSnapshot dataSnap : dataSnapshot.getChildren())
 *                               {
 *                                   //   Double rate = dataSnapshot1.child("worker_rating").getValue(Double.class);
 *                                   double rating = Double.parseDouble(dataSnap.child("user_rating").getValue().toString());
 *                                   total = total + rating;
 *                                   counter = counter + 1;
 *                                     name = dataSnap.child("name").getValue().toString();
 *
 *                               }
 *
 *                               Log.d("totalRating", "onChildAdded: "+total/counter+"\n"+name);
 *
 *
 *                               Map rating  = new HashMap<>();
 *                               rating.put("average_rating",total/counter);
 *                               rating.put("total_rating",counter);
 *                               dbRef.child(key).updateChildren(rating);
 *
 *
 *                           }
 *
 *                           @Override
 *                           public void onCancelled(@NonNull DatabaseError databaseError) {}});
 *
 *
 *
 *
 *
 *                   }
 *                   @Override
 *                   public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) { }
 *                   @Override
 *                   public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {}
 *                   @Override
 *                   public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) { }
 *
 *                   @Override
 *                   public void onCancelled(@NonNull DatabaseError databaseError) { }
 *               });
 *
 *     }
 *
 *
 *
 */