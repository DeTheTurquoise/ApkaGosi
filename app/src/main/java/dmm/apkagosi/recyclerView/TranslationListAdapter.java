package dmm.apkagosi.recyclerView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import dmm.apkagosi.R;

/**
 * Created by ddabrowa on 2017-05-29.
 */

public class TranslationListAdapter extends RecyclerView.Adapter<TranslationListAdapter.NumberViewHolder>{

    private int numberOfResultsToDisplay;

    public void setJishoWord(String[] jishoWord, String[] jishoReading, String[]jishoDefinition, int numberOfResultsToDisplay) {
        this.jishoWord = jishoWord;
        this.jishoReading = jishoReading;
        this.jishoDefinition = jishoDefinition;
        this.numberOfResultsToDisplay = numberOfResultsToDisplay;
    }

    private String[] jishoWord;
    private String[] jishoReading;
    private String[] jishoDefinition;


    /**
     * @param numberOfResultsToDisplay - describes how many of searched result will be displayed
     */
    public TranslationListAdapter(int numberOfResultsToDisplay) {
        this.numberOfResultsToDisplay = numberOfResultsToDisplay;
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

    public class NumberViewHolder extends RecyclerView.ViewHolder{
        public NumberViewHolder(View itemView) {
            super(itemView);
            translationReading = (TextView) itemView.findViewById(R.id.translate_text_reading);
            translationText = (TextView) itemView.findViewById(R.id.translate_text_translated);
            translationDefinition = (TextView) itemView.findViewById(R.id.translate_description);
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
    }
}
