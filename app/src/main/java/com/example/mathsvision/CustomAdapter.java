package com.example.mathsvision;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CustomAdapter extends RecyclerView.Adapter <CustomAdapter.MyViewHolder> {

    private Context context;
    private ArrayList fname,name,classes,id;
    int n ;
    ArrayList<Integer> selected;
    static  ArrayList<String> delId;
    boolean flag =false;

    private RecyclerViewClickListener listener;

    public CustomAdapter(Context context, ArrayList fname, ArrayList name,  RecyclerViewClickListener listener,ArrayList classes,ArrayList id,int n) {
        this.fname = fname;
        this.context=context;
        this.name = name;
        this.classes=classes;
        this.id=id;
        this.n=n;
        this.selected=new ArrayList<Integer>();
        this.delId=new ArrayList<String>();
        this.listener=listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.activity_recycle_row,parent,false);
        return new MyViewHolder(view);
    }

    public Bitmap con(byte[] image)
    {
        return  BitmapFactory.decodeByteArray(image, 0, image.length);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {

        holder.usernamet.setText(String.valueOf(fname.get(position)));
        holder.namet.setText(String.valueOf(name.get(position)));
        holder.classest.setText(String.valueOf(classes.get(position)));
        holder.idt.setText(String.valueOf(id.get(position)));

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (n == 3) {
                    holder.row.setBackgroundResource(R.drawable.ripple2);
                    delId.add(holder.idt.getText().toString());
                    flag = true;
                    // System.out.println(holder.namet.getText().toString());
                }
                return true;
            }
        });
if (n==3){
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (delId.size() == 1 && delId.contains(holder.idt.getText().toString()) && flag) {
                    holder.row.setBackgroundColor(Color.WHITE);
                    flag = false;
                    delId.clear();
                } else if (flag) {
                    if (delId.contains(holder.idt.getText().toString())) {
                        holder.row.setBackgroundColor(Color.WHITE);
                        delId.remove(holder.idt.getText().toString());
                    } else {
                        holder.row.setBackgroundResource(R.drawable.ripple2);
                        delId.add(holder.idt.getText().toString());
                    }
                }

            }
        });
    }
    }

    @Override
    public int getItemCount() {
        return fname.size();
    }

    public interface RecyclerViewClickListener{
        void onClick(View v, int position);
    }
    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

       TextView usernamet,namet,classest,idt;
        ConstraintLayout row;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            usernamet=itemView.findViewById(R.id.usernamet);
            namet=itemView.findViewById(R.id.namet);
            row=itemView.findViewById(R.id.rowc);
            classest=itemView.findViewById(R.id.classest);
            idt=itemView.findViewById(R.id.idt);

            if (n!=3)
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
listener.onClick(itemView,getAdapterPosition());
        }
    }

}
