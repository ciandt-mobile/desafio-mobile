package br.com.ciandt.application.fellini.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import br.com.ciandt.application.fellini.BuildConfig;
import br.com.ciandt.application.fellini.MovieApplication;
import br.com.ciandt.application.fellini.R;
import br.com.ciandt.application.fellini.domain.crew.Actor;
import br.com.ciandt.application.fellini.domain.crew.Credit;
import br.com.ciandt.application.fellini.service.MovieRepository;
import br.com.ciandt.application.fellini.service.callbacks.OnGettingCreditsCallback;
import br.com.ciandt.application.fellini.service.legacycode.CrewService;
import br.com.ciandt.application.fellini.ui.datagroups.GeneralAdapter;
import br.com.ciandt.application.fellini.ui.datagroups.viewholders.CrewViewHolder;
import br.com.ciandt.application.fellini.utils.CollectionHandlingUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CrewOverviewActivity extends AppCompatActivity {

    private int movieId;
    private RecyclerView allCrewList, allCastList;
    private GeneralAdapter<Actor> generalCrewAdapter, generalCastAdapter;

    private static final int MAX_CREW_ENTRIES = 16;

    private MovieRepository repository;

    private CollectionHandlingUtil<Actor> collectionHandlingUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crew_overview);

        collectionHandlingUtil = new CollectionHandlingUtil<>();

        movieId = getIntent().getIntExtra("movie_id", 0);

        repository = MovieRepository.getInstance();

        allCastList = findViewById(R.id.all_cast_list);
        allCrewList = findViewById(R.id.all_crew_list);

        repository.getMovieCredits(movieId, new OnGettingCreditsCallback() {
            @Override
            public void onSuccess(Credit credit) {

                generalCastAdapter = new GeneralAdapter<Actor>(CrewOverviewActivity.this, collectionHandlingUtil.limitListMaxViews(credit.getCast(), MAX_CREW_ENTRIES)) {
                    @Override
                    public RecyclerView.ViewHolder setViewHolder(ViewGroup parent, int viewType) {
                        return new CrewViewHolder(LayoutInflater.from(CrewOverviewActivity.this).inflate(R.layout.crew_details_item, parent, false));
                    }

                    @Override
                    public void onBindData(RecyclerView.ViewHolder viewHolder, Actor item) {
                        CrewViewHolder crewViewHolder = (CrewViewHolder) viewHolder;
                        crewViewHolder.bind(item);
                    }
                };

                generalCrewAdapter = new GeneralAdapter<Actor>(CrewOverviewActivity.this, collectionHandlingUtil.limitListMaxViews(credit.getCrew(), MAX_CREW_ENTRIES)) {
                    @Override
                    public RecyclerView.ViewHolder setViewHolder(ViewGroup parent, int viewType) {
                        return new CrewViewHolder(LayoutInflater.from(CrewOverviewActivity.this).inflate(R.layout.crew_details_item, parent, false));
                    }

                    @Override
                    public void onBindData(RecyclerView.ViewHolder viewHolder, Actor item) {
                        CrewViewHolder crewViewHolder = (CrewViewHolder) viewHolder;
                        crewViewHolder.bind(item);
                    }
                };

                allCrewList.setAdapter(generalCrewAdapter);
                allCastList.setAdapter(generalCastAdapter);
            }

            @Override
            public void onError() {

            }
        });

    }

}
