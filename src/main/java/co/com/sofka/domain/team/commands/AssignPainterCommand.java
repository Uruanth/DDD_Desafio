package co.com.sofka.domain.team.commands;

import co.com.sofka.domain.generic.Command;
import co.com.sofka.domain.generics.PersonalInformation;
import co.com.sofka.domain.team.values.PaintType;
import co.com.sofka.domain.team.values.PainterId;
import co.com.sofka.domain.team.values.TeamId;
import co.com.sofka.domain.team.values.Technique;

public class AssignPainterCommand extends Command {
    private final TeamId teamId;
    private final PainterId painterId;
    private final PaintType paintType;
    private final PersonalInformation personalInformation;
    private final Technique technique;

    public TeamId getTeamId() {
        return teamId;
    }

    public PainterId getPainterId() {
        return painterId;
    }

    public PaintType getPaintType() {
        return paintType;
    }

    public PersonalInformation getPersonalInformation() {
        return personalInformation;
    }

    public Technique getTechnique() {
        return technique;
    }

    public AssignPainterCommand(TeamId teamId, PainterId painterId, PaintType paintType, PersonalInformation personalInformation, Technique technique) {
        this.teamId = teamId;
        this.painterId = painterId;
        this.paintType = paintType;
        this.personalInformation = personalInformation;
        this.technique = technique;
    }


}
