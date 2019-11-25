package br.com.ciandt.application.fellini.ui.datagroups.viewholders;

import android.support.annotation.NonNull;
import android.support.design.chip.Chip;
import android.view.View;

import br.com.ciandt.application.fellini.R;
import br.com.ciandt.application.fellini.domain.movie.Genre;
import br.com.ciandt.application.fellini.ui.datagroups.GenericViewHolder;

public class GenreViewHolder extends GenericViewHolder<Genre> {

    private Chip chip;

    public GenreViewHolder(@NonNull View itemView) {
        super(itemView);

        chip = itemView.findViewById(R.id.genre_chip);
    }

    @Override
    public void bind(Genre genre) {
        chip.setText(genre.getName());
    }
}
