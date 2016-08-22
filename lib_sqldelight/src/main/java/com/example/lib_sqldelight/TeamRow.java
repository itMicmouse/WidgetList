package com.example.lib_sqldelight;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lib_sqldelight.db.Team;

import butterknife.BindView;
import butterknife.ButterKnife;

import java.text.SimpleDateFormat;

public final class TeamRow extends LinearLayout {
//    @BindView(R.id.team_name)
    TextView teamName;
//    @BindView(R.id.coach_name)
    TextView coachName;
//    @BindView(R.id.founded)
    TextView founded;

    private SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

    public TeamRow(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
    }

    public void populate(Team team) {
        teamName.setText(team.name());
        coachName.setText(team.coach());
        founded.setText(getContext().getString(R.string.founded, df.format(team.founded().getTime())));
    }
}
