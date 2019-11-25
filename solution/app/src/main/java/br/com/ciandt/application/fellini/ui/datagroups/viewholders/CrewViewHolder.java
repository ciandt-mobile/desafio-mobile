package br.com.ciandt.application.fellini.ui.datagroups.viewholders;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import br.com.ciandt.application.fellini.R;
import br.com.ciandt.application.fellini.domain.crew.Actor;
import br.com.ciandt.application.fellini.ui.datagroups.GenericViewHolder;
import de.hdodenhof.circleimageview.CircleImageView;

public class CrewViewHolder extends GenericViewHolder<Actor> {

    private CircleImageView profilePath;
    private TextView name, character;

    public CrewViewHolder(@NonNull View itemView) {
        super(itemView);

        profilePath = itemView.findViewById(R.id.actor_profile_pic_path);
        name = itemView.findViewById(R.id.actor_name);
        character = itemView.findViewById(R.id.actor_character);
    }

    @Override
    public void bind(Actor actor) {
        Picasso.get().load(actor.getProfilePath()).placeholder(R.drawable.ic_account_circle_256dp).fit().centerCrop().into(profilePath);
        name.setText(actor.getName());
        if (actor.getCharacter() != null) {
            character.setText(actor.getCharacter());
        } else {
            character.setVisibility(View.GONE);
        }
    }
}
