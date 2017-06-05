package dmm.apkagosi.recyclerView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.view.View.OnClickListener;

import dmm.apkagosi.R;

/**
 * Created by ddabrowa on 2017-05-29.
 */

public class TranslationListAdapter extends RecyclerView.Adapter<TranslationListAdapter.NumberViewHolder>{

    private int numberOfResultsToDisplay;
    final private ListItemClickListener clickListener;

    public void setJishoWord(String[] jishoWord, String[] jishoReading, String[]jishoDefinition, int numberOfResultsToDisplay) {
        this.jishoWord = jishoWord;
        this.jishoReading = jishoReading;
        this.jishoDefinition = jishoDefinition;
        this.numberOfResultsToDisplay = numberOfResultsToDisplay;
    }

    private String[] jishoWord;
    private String[] jishoReading;
    private String[] jishoDefinition;
    private String[] jishoTags;


    /**
     * @param numberOfResultsToDisplay - describes how many of searched result will be displayed
     */
    public TranslationListAdapter(int numberOfResultsToDisplay, ListItemClickListener clickListener) {
        this.numberOfResultsToDisplay = numberOfResultsToDisplay;
        this.clickListener = clickListener;
    }

    /**
     * The interface that receives onClick messages, when recyclerView item is clicked
     */
    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }

    /**
     * Creates ViewHolder
     * @param parent - ViewGroup triggering this ViewHolder (TranslateActivity?)
     * @param viewType - type of items (TextViews for parts of translation)
     * @return viewHolder - ne ViewHolder for translated text
     */
    @Override
    public NumberViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.translation_list;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately);
        NumberViewHolder viewHolder = new NumberViewHolder(view);


        return viewHolder;
    }

    /**
     * Display translation on correct position
     * @param holder - ViewHolder which holds parts of translation for current label
     * @param position - order number for translation result
     */
    @Override
    public void onBindViewHolder(NumberViewHolder holder, int position) {
        holder.bind(position);
    }

    /**
     * @return number of translation results to display
     */
    @Override
    public int getItemCount() {
        return numberOfResultsToDisplay;
    }

    public class NumberViewHolder extends RecyclerView.ViewHolder implements OnClickListener {
        public NumberViewHolder(View itemView) {
            super(itemView);
            translationReading = (TextView) itemView.findViewById(R.id.translate_text_reading);
            translationText = (TextView) itemView.findViewById(R.id.translate_text_translated);
            translationDefinition = (TextView) itemView.findViewById(R.id.translate_description);
            itemView.setOnClickListener(this);
        }

        private TextView translationReading;
        private TextView translationText;
        private TextView translationDefinition;


        /**
         * Displays number of translation result in order
         * @param itemIndex - translation result number
         */
        void bind(int itemIndex){
            translationReading.setText(jishoReading[itemIndex]);
            translationText.setText(jishoWord[itemIndex]);
            translationDefinition.setText(jishoDefinition[itemIndex]);
        }

        @Override
        public void onClick(View v) {
            clickListener.onListItemClick(getAdapterPosition());
        }
    }
}
