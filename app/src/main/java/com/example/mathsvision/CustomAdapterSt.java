package com.example.mathsvision;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class CustomAdapterSt extends RecyclerView.Adapter <CustomAdapterSt.MyViewHolder> {

    private Context context;
    private ArrayList fname,name,classes,id,img;
    int n ;
    ArrayList<Integer> selected;
    static  ArrayList<String> delId;
    boolean flag =false;

    private RecyclerViewClickListener listener;

    public CustomAdapterSt(Context context, ArrayList fname, ArrayList name, RecyclerViewClickListener listener, ArrayList classes, ArrayList id, int n,ArrayList img) {
        this.fname = fname;
        this.context=context;
        this.name = name;
        this.classes=classes;
        this.id=id;
        this.n=n;
        this.img=img;
        this.selected=new ArrayList<Integer>();
        this.delId=new ArrayList<String>();
        this.listener=listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.activity_srecycle_row,parent,false);
        return new MyViewHolder(view);
    }

    public Bitmap con(byte[] image)
    {
        return  BitmapFactory.decodeByteArray(image, 0, image.length);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {

        holder.stfnamet.setText(String.valueOf(fname.get(position)));
        holder.stnamet.setText(String.valueOf(name.get(position)));
        holder.classest.setText(String.valueOf(classes.get(position)));
        holder.idt.setText(String.valueOf(id.get(position)));
        holder.imaget.setImageBitmap(con((byte[])img.get(position)));
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

       TextView stfnamet,stnamet,classest,idt;
       CircleImageView imaget;
        ConstraintLayout row;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            stfnamet=itemView.findViewById(R.id.stfnamet);
            stnamet=itemView.findViewById(R.id.stnamet);
            row=itemView.findViewById(R.id.rowcs);
            classest=itemView.findViewById(R.id.sclassest);
            idt=itemView.findViewById(R.id.sidt);
            imaget=(CircleImageView) itemView.findViewById(R.id.stcircularimgt);
            if (n!=3)
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
listener.onClick(itemView,getAdapterPosition());
        }
    }

}
