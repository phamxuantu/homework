package com.example.xuantu.androidky9;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Xuan Tu on 28/08/2017.
 */

public class CustomListStudent extends BaseAdapter implements Filterable {
    Context context;
    ArrayList<Student> students;
    CustomFilter filter;
    ArrayList<Student> filterList;

    public CustomListStudent(Context context, ArrayList<Student> students){
        this.context = context;
        this.students = students;
        this.filterList = students;
    }

    public class ViewHolder {
        TextView txtvName;
        TextView txtvSex;
        TextView txtvSkill;
    }

    @Override
    public int getCount() {
        return students.size();
    }

    @Override
    public Object getItem(int position) {
        return students.get(position);
    }

    @Override
    public long getItemId(int position) {
        return students.indexOf(getItem(position));
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(convertView == null){
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.custom_list_student, null);
            holder.txtvName = (TextView) convertView.findViewById(R.id.txtvName);
            holder.txtvSex = (TextView) convertView.findViewById(R.id.txtvSex);
            holder.txtvSkill = (TextView) convertView.findViewById(R.id.txtvSkill);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.txtvName.setText(students.get(position).getName());
        holder.txtvSex.setText(students.get(position).getSex());
        holder.txtvSkill.setText(students.get(position).getSkill());
        return convertView;
    }


    @Override
    public Filter getFilter() {
        if(filter == null){
            filter = new CustomFilter();
        }
        return filter;
    }

    class CustomFilter extends Filter{

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            if(constraint != null && constraint.length() > 0){
                constraint = constraint.toString().toUpperCase();
                ArrayList<Student> filters = new ArrayList<Student>();
                for (int i = 0; i < filterList.size(); i++){
                    if(filterList.get(i).getName().toUpperCase().contains(constraint)){
                        Student s = new Student(filterList.get(i).getName(),
                                filterList.get(i).getSex(),
                                filterList.get(i).getSkill());
                        filters.add(s);
                    }
                }
                results.count = filters.size();
                results.values = filters;
            }else {
                results.count = filterList.size();
                results.values = filterList;
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            students = (ArrayList<Student>) results.values;
            notifyDataSetChanged();
        }
    }
}
