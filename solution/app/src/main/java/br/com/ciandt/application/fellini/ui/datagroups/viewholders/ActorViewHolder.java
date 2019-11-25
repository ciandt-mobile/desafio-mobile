package br.com.ciandt.application.fellini.ui.datagroups.viewholders;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import br.com.ciandt.application.fellini.R;
import br.com.ciandt.application.fellini.domain.crew.Actor;
import br.com.ciandt.application.fellini.ui.datagroups.GenericViewHolder;

public class ActorViewHolder extends GenericViewHolder<Actor> {

    private CardView cardViewItemLayout;
    private ImageView profilePicture;
    private TextView actorFullName, inMovieCharacterName;

    public ActorViewHolder(@NonNull View itemView) {
        super(itemView);

        cardViewItemLayout = itemView.findViewById(R.id.card_view_item_layout);
        profilePicture = itemView.findViewById(R.id.actor_item_profile_picture);
        actorFullName = itemView.findViewById(R.id.actor_item_name);
        inMovieCharacterName = itemView.findViewById(R.id.actor_in_movie_character_name);
    }

    @Override
    public void bind(Actor actor) {
        Picasso.get().load(actor.getProfilePath()).placeholder(R.drawable.ic_face_256dp).fit().centerCrop().into(profilePicture);
        actorFullName.setText(actor.getName());
        inMovieCharacterName.setText(actor.getCharacter());
    }

}
