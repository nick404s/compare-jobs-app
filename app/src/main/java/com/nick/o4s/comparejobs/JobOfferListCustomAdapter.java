package com.nick.o4s.comparejobs;


import android.graphics.Color;
import android.graphics.Typeface;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class JobOfferListCustomAdapter extends RecyclerView.Adapter<JobOfferListCustomAdapter.ViewHolder> {

    private final RecyclerViewInterface recyclerViewInterface;
    private ArrayList<JobInfo> jobOfferList;

    public final Queue<Integer> selectedJobsPositions = new LinkedList<>();

    public ArrayList<JobInfo> getSelectedJobs() {
        ArrayList<JobInfo> selectedJobs = new ArrayList<>();
        for (Integer position : selectedJobsPositions) {
            selectedJobs.add(jobOfferList.get(position));
        }
        return selectedJobs;
    }

    private void pushSelectedJob(Integer jobPosition) {
        selectedJobsPositions.add(jobPosition);
    }

    private Integer popSelectedJob() {
        return selectedJobsPositions.remove();
    }

    public void selectJob(int position) {
        Log.d("JobOfferListCustomAdapter", "currently selected jobs: " + selectedJobsPositions);
        Log.d("JobOfferListCustomAdapter", "selectJob: " + position);
        if (selectedJobsPositions.contains(position)) {
            selectedJobsPositions.remove(position);
            notifyItemChanged(position);
            return;
        }

        if (selectedJobsPositions.size() == 2) {
            notifyItemChanged(popSelectedJob());
        }
        pushSelectedJob(position);
        notifyItemChanged(position);
    }

    /**
     * Initialize the dataset of the Adapter
     *
     * @param jobOfferList String[] containing the data to populate views to be used
     *                by RecyclerView
     */
    public JobOfferListCustomAdapter(ArrayList<JobInfo> jobOfferList, RecyclerViewInterface recyclerViewInterface) {
        this.jobOfferList = jobOfferList;
        this.recyclerViewInterface = recyclerViewInterface;

    }

    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.text_row_item, viewGroup, false);

        return new ViewHolder(view, recyclerViewInterface);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {

        int highlightedColor = viewHolder.getTextView().getContext().getResources().getColor(R.color.selectedJobOffer, null);
        int unhighlightedColor = Color.TRANSPARENT;

        // If the position is in the selectedJobPositions, highlight it.
        if (selectedJobsPositions.contains(position)) {
            viewHolder.itemView.setBackgroundColor(highlightedColor);
        } else {
            viewHolder.itemView.setBackgroundColor(unhighlightedColor);
        }

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        JobInfo jobInfo = jobOfferList.get(position);
        SpannableStringBuilder jobText = new SpannableStringBuilder(jobInfo.getTitle() + " - " + jobInfo.getCompany());
        if (jobInfo.isCurrentJob()) {
            SpannableStringBuilder currentJobText = new SpannableStringBuilder(" (Current Job)");
            currentJobText.setSpan(new StyleSpan(Typeface.ITALIC), 0, currentJobText.length(), 0);
            jobText.append(currentJobText);
        }
        viewHolder.getTextView().setText(jobText);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return jobOfferList.size();
    }

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final FrameLayout frameLayout;
        private final TextView textView;

        public ViewHolder(View view, RecyclerViewInterface recyclerViewInterface) {
            super(view);
            // Define click listener for the ViewHolder's View

            frameLayout = view.findViewById(R.id.rowFrameLayout);
            textView = view.findViewById(R.id.textRow);
            frameLayout.setOnClickListener(v -> {
                if (recyclerViewInterface == null) {
                    return;
                }
                int position = getAdapterPosition();
                if (position == RecyclerView.NO_POSITION) {
                    return;
                }
                recyclerViewInterface.onItemClick(position);
            });
        }

        public TextView getTextView() {
            return textView;
        }

    }
    public void updateJobOffers(ArrayList<JobInfo> jobOffersList) {
        this.jobOfferList = jobOffersList;
    }
}
