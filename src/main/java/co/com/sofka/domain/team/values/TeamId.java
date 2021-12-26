package co.com.sofka.domain.team.values;

import co.com.sofka.domain.generic.Identity;

public class TeamId extends Identity {
    private TeamId(String value) {
        super(value);
    }

    public TeamId() {
    }

    public static TeamId from(String value) {
        return new TeamId(value);
    }
}
