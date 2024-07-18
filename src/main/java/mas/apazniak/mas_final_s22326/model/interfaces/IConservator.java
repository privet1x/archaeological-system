package mas.apazniak.mas_final_s22326.model.interfaces;

import mas.apazniak.mas_final_s22326.enumm.MissionState;

public interface IConservator {
    void preserveArtifacts(String preservationTechnique);
    void updateMissionState(MissionState state);
}
