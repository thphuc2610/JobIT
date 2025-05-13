package com.r2s.findInternship.infracstructure.persistence.mapper;

import com.r2s.findInternship.application.dto.MajorDTO;
import com.r2s.findInternship.application.dto.PositionDTO;
import com.r2s.findInternship.application.dto.ScheduleDTO;
import com.r2s.findInternship.application.dto.UniversityDTO;
import com.r2s.findInternship.application.dto.candidate.CandidateCreationDTO;
import com.r2s.findInternship.application.dto.candidate.CandidateDTO;
import com.r2s.findInternship.application.dto.candidate.CandidateOtherInfoDTO;
import com.r2s.findInternship.application.dto.candidate.CandidateProfileDTO;
import com.r2s.findInternship.domain.entity.Candidate;
import com.r2s.findInternship.domain.entity.CandidateMajor;
import com.r2s.findInternship.domain.entity.CandidatePosition;
import com.r2s.findInternship.domain.entity.CandidateSchedule;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-10T12:54:20+0700",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.11 (Oracle Corporation)"
)
@Component
public class CandidateMapperImpl implements CandidateMapper {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UniversityMapper universityMapper;
    @Autowired
    private PositionMapper positionMapper;
    @Autowired
    private ScheduleMapper scheduleMapper;
    @Autowired
    private MajorMapper majorMapper;

    @Override
    public Candidate toEntity(CandidateCreationDTO candidateCreationDTO) {
        if ( candidateCreationDTO == null ) {
            return null;
        }

        Candidate candidate = new Candidate();

        candidate.setUser( userMapper.toEntity( candidateCreationDTO.getUserCreationDTO() ) );
        Boolean searchable = candidateCreationDTOCandidateOtherInfoDTOSearchable( candidateCreationDTO );
        if ( searchable != null ) {
            candidate.setSearchable( searchable );
        }
        candidate.setCV( candidateCreationDTOCandidateOtherInfoDTOCV( candidateCreationDTO ) );
        candidate.setReferenceLetter( candidateCreationDTOCandidateOtherInfoDTOReferenceLetter( candidateCreationDTO ) );
        candidate.setUniversity( universityMapper.toEntity( candidateCreationDTOCandidateOtherInfoDTOUniversityDTO( candidateCreationDTO ) ) );
        candidate.setDesiredJob( candidateCreationDTOCandidateOtherInfoDTODesiredJob( candidateCreationDTO ) );
        candidate.setDesiredWorkingProvince( candidateCreationDTOCandidateOtherInfoDTODesiredWorkingProvince( candidateCreationDTO ) );

        return candidate;
    }

    @Override
    public Candidate toEntity(CandidateProfileDTO candidateProfileDTO) {
        if ( candidateProfileDTO == null ) {
            return null;
        }

        Candidate candidate = new Candidate();

        candidate.setUser( userMapper.toEntity( candidateProfileDTO.getUserProfileDTO() ) );
        Boolean searchable = candidateProfileDTOCandidateOtherInfoDTOSearchable( candidateProfileDTO );
        if ( searchable != null ) {
            candidate.setSearchable( searchable );
        }
        candidate.setCV( candidateProfileDTOCandidateOtherInfoDTOCV( candidateProfileDTO ) );
        candidate.setReferenceLetter( candidateProfileDTOCandidateOtherInfoDTOReferenceLetter( candidateProfileDTO ) );
        candidate.setUniversity( universityMapper.toEntity( candidateProfileDTOCandidateOtherInfoDTOUniversityDTO( candidateProfileDTO ) ) );
        candidate.setDesiredJob( candidateProfileDTOCandidateOtherInfoDTODesiredJob( candidateProfileDTO ) );
        candidate.setDesiredWorkingProvince( candidateProfileDTOCandidateOtherInfoDTODesiredWorkingProvince( candidateProfileDTO ) );

        return candidate;
    }

    @Override
    public CandidateDTO toDTO(Candidate candidate) {
        if ( candidate == null ) {
            return null;
        }

        CandidateDTO.CandidateDTOBuilder candidateDTO = CandidateDTO.builder();

        candidateDTO.candidateOtherInfoDTO( candidateToCandidateOtherInfoDTO( candidate ) );
        candidateDTO.userDTO( userMapper.toDTO( candidate.getUser() ) );
        candidateDTO.id( candidate.getId() );

        return candidateDTO.build();
    }

    @Override
    public Candidate toEntity(CandidateDTO candidateDTO) {
        if ( candidateDTO == null ) {
            return null;
        }

        Candidate candidate = new Candidate();

        candidate.setUser( userMapper.toEntity( candidateDTO.getUserDTO() ) );
        Boolean searchable = candidateDTOCandidateOtherInfoDTOSearchable( candidateDTO );
        if ( searchable != null ) {
            candidate.setSearchable( searchable );
        }
        candidate.setCV( candidateDTOCandidateOtherInfoDTOCV( candidateDTO ) );
        candidate.setReferenceLetter( candidateDTOCandidateOtherInfoDTOReferenceLetter( candidateDTO ) );
        candidate.setUniversity( universityMapper.toEntity( candidateDTOCandidateOtherInfoDTOUniversityDTO( candidateDTO ) ) );
        candidate.setDesiredJob( candidateDTOCandidateOtherInfoDTODesiredJob( candidateDTO ) );
        candidate.setDesiredWorkingProvince( candidateDTOCandidateOtherInfoDTODesiredWorkingProvince( candidateDTO ) );
        if ( candidateDTO.getId() != null ) {
            candidate.setId( candidateDTO.getId() );
        }

        return candidate;
    }

    private Boolean candidateCreationDTOCandidateOtherInfoDTOSearchable(CandidateCreationDTO candidateCreationDTO) {
        if ( candidateCreationDTO == null ) {
            return null;
        }
        CandidateOtherInfoDTO candidateOtherInfoDTO = candidateCreationDTO.getCandidateOtherInfoDTO();
        if ( candidateOtherInfoDTO == null ) {
            return null;
        }
        Boolean searchable = candidateOtherInfoDTO.getSearchable();
        if ( searchable == null ) {
            return null;
        }
        return searchable;
    }

    private String candidateCreationDTOCandidateOtherInfoDTOCV(CandidateCreationDTO candidateCreationDTO) {
        if ( candidateCreationDTO == null ) {
            return null;
        }
        CandidateOtherInfoDTO candidateOtherInfoDTO = candidateCreationDTO.getCandidateOtherInfoDTO();
        if ( candidateOtherInfoDTO == null ) {
            return null;
        }
        String cV = candidateOtherInfoDTO.getCV();
        if ( cV == null ) {
            return null;
        }
        return cV;
    }

    private String candidateCreationDTOCandidateOtherInfoDTOReferenceLetter(CandidateCreationDTO candidateCreationDTO) {
        if ( candidateCreationDTO == null ) {
            return null;
        }
        CandidateOtherInfoDTO candidateOtherInfoDTO = candidateCreationDTO.getCandidateOtherInfoDTO();
        if ( candidateOtherInfoDTO == null ) {
            return null;
        }
        String referenceLetter = candidateOtherInfoDTO.getReferenceLetter();
        if ( referenceLetter == null ) {
            return null;
        }
        return referenceLetter;
    }

    private UniversityDTO candidateCreationDTOCandidateOtherInfoDTOUniversityDTO(CandidateCreationDTO candidateCreationDTO) {
        if ( candidateCreationDTO == null ) {
            return null;
        }
        CandidateOtherInfoDTO candidateOtherInfoDTO = candidateCreationDTO.getCandidateOtherInfoDTO();
        if ( candidateOtherInfoDTO == null ) {
            return null;
        }
        UniversityDTO universityDTO = candidateOtherInfoDTO.getUniversityDTO();
        if ( universityDTO == null ) {
            return null;
        }
        return universityDTO;
    }

    private String candidateCreationDTOCandidateOtherInfoDTODesiredJob(CandidateCreationDTO candidateCreationDTO) {
        if ( candidateCreationDTO == null ) {
            return null;
        }
        CandidateOtherInfoDTO candidateOtherInfoDTO = candidateCreationDTO.getCandidateOtherInfoDTO();
        if ( candidateOtherInfoDTO == null ) {
            return null;
        }
        String desiredJob = candidateOtherInfoDTO.getDesiredJob();
        if ( desiredJob == null ) {
            return null;
        }
        return desiredJob;
    }

    private String candidateCreationDTOCandidateOtherInfoDTODesiredWorkingProvince(CandidateCreationDTO candidateCreationDTO) {
        if ( candidateCreationDTO == null ) {
            return null;
        }
        CandidateOtherInfoDTO candidateOtherInfoDTO = candidateCreationDTO.getCandidateOtherInfoDTO();
        if ( candidateOtherInfoDTO == null ) {
            return null;
        }
        String desiredWorkingProvince = candidateOtherInfoDTO.getDesiredWorkingProvince();
        if ( desiredWorkingProvince == null ) {
            return null;
        }
        return desiredWorkingProvince;
    }

    private Boolean candidateProfileDTOCandidateOtherInfoDTOSearchable(CandidateProfileDTO candidateProfileDTO) {
        if ( candidateProfileDTO == null ) {
            return null;
        }
        CandidateOtherInfoDTO candidateOtherInfoDTO = candidateProfileDTO.getCandidateOtherInfoDTO();
        if ( candidateOtherInfoDTO == null ) {
            return null;
        }
        Boolean searchable = candidateOtherInfoDTO.getSearchable();
        if ( searchable == null ) {
            return null;
        }
        return searchable;
    }

    private String candidateProfileDTOCandidateOtherInfoDTOCV(CandidateProfileDTO candidateProfileDTO) {
        if ( candidateProfileDTO == null ) {
            return null;
        }
        CandidateOtherInfoDTO candidateOtherInfoDTO = candidateProfileDTO.getCandidateOtherInfoDTO();
        if ( candidateOtherInfoDTO == null ) {
            return null;
        }
        String cV = candidateOtherInfoDTO.getCV();
        if ( cV == null ) {
            return null;
        }
        return cV;
    }

    private String candidateProfileDTOCandidateOtherInfoDTOReferenceLetter(CandidateProfileDTO candidateProfileDTO) {
        if ( candidateProfileDTO == null ) {
            return null;
        }
        CandidateOtherInfoDTO candidateOtherInfoDTO = candidateProfileDTO.getCandidateOtherInfoDTO();
        if ( candidateOtherInfoDTO == null ) {
            return null;
        }
        String referenceLetter = candidateOtherInfoDTO.getReferenceLetter();
        if ( referenceLetter == null ) {
            return null;
        }
        return referenceLetter;
    }

    private UniversityDTO candidateProfileDTOCandidateOtherInfoDTOUniversityDTO(CandidateProfileDTO candidateProfileDTO) {
        if ( candidateProfileDTO == null ) {
            return null;
        }
        CandidateOtherInfoDTO candidateOtherInfoDTO = candidateProfileDTO.getCandidateOtherInfoDTO();
        if ( candidateOtherInfoDTO == null ) {
            return null;
        }
        UniversityDTO universityDTO = candidateOtherInfoDTO.getUniversityDTO();
        if ( universityDTO == null ) {
            return null;
        }
        return universityDTO;
    }

    private String candidateProfileDTOCandidateOtherInfoDTODesiredJob(CandidateProfileDTO candidateProfileDTO) {
        if ( candidateProfileDTO == null ) {
            return null;
        }
        CandidateOtherInfoDTO candidateOtherInfoDTO = candidateProfileDTO.getCandidateOtherInfoDTO();
        if ( candidateOtherInfoDTO == null ) {
            return null;
        }
        String desiredJob = candidateOtherInfoDTO.getDesiredJob();
        if ( desiredJob == null ) {
            return null;
        }
        return desiredJob;
    }

    private String candidateProfileDTOCandidateOtherInfoDTODesiredWorkingProvince(CandidateProfileDTO candidateProfileDTO) {
        if ( candidateProfileDTO == null ) {
            return null;
        }
        CandidateOtherInfoDTO candidateOtherInfoDTO = candidateProfileDTO.getCandidateOtherInfoDTO();
        if ( candidateOtherInfoDTO == null ) {
            return null;
        }
        String desiredWorkingProvince = candidateOtherInfoDTO.getDesiredWorkingProvince();
        if ( desiredWorkingProvince == null ) {
            return null;
        }
        return desiredWorkingProvince;
    }

    protected List<PositionDTO> candidatePositionListToPositionDTOList(List<CandidatePosition> list) {
        if ( list == null ) {
            return null;
        }

        List<PositionDTO> list1 = new ArrayList<PositionDTO>( list.size() );
        for ( CandidatePosition candidatePosition : list ) {
            list1.add( positionMapper.toPositionDTO( candidatePosition ) );
        }

        return list1;
    }

    protected List<ScheduleDTO> candidateScheduleListToScheduleDTOList(List<CandidateSchedule> list) {
        if ( list == null ) {
            return null;
        }

        List<ScheduleDTO> list1 = new ArrayList<ScheduleDTO>( list.size() );
        for ( CandidateSchedule candidateSchedule : list ) {
            list1.add( scheduleMapper.toScheduleDTO( candidateSchedule ) );
        }

        return list1;
    }

    protected List<MajorDTO> candidateMajorListToMajorDTOList(List<CandidateMajor> list) {
        if ( list == null ) {
            return null;
        }

        List<MajorDTO> list1 = new ArrayList<MajorDTO>( list.size() );
        for ( CandidateMajor candidateMajor : list ) {
            list1.add( majorMapper.toMajorDTO( candidateMajor ) );
        }

        return list1;
    }

    protected CandidateOtherInfoDTO candidateToCandidateOtherInfoDTO(Candidate candidate) {
        if ( candidate == null ) {
            return null;
        }

        CandidateOtherInfoDTO.CandidateOtherInfoDTOBuilder candidateOtherInfoDTO = CandidateOtherInfoDTO.builder();

        candidateOtherInfoDTO.searchable( candidate.isSearchable() );
        candidateOtherInfoDTO.CV( candidate.getCV() );
        candidateOtherInfoDTO.referenceLetter( candidate.getReferenceLetter() );
        candidateOtherInfoDTO.universityDTO( universityMapper.toDTO( candidate.getUniversity() ) );
        candidateOtherInfoDTO.desiredJob( candidate.getDesiredJob() );
        candidateOtherInfoDTO.desiredWorkingProvince( candidate.getDesiredWorkingProvince() );
        candidateOtherInfoDTO.positionDTOs( candidatePositionListToPositionDTOList( candidate.getCandidatePositions() ) );
        candidateOtherInfoDTO.scheduleDTOs( candidateScheduleListToScheduleDTOList( candidate.getCandidateSchedules() ) );
        candidateOtherInfoDTO.majorDTOs( candidateMajorListToMajorDTOList( candidate.getCandidateMajors() ) );

        return candidateOtherInfoDTO.build();
    }

    private Boolean candidateDTOCandidateOtherInfoDTOSearchable(CandidateDTO candidateDTO) {
        if ( candidateDTO == null ) {
            return null;
        }
        CandidateOtherInfoDTO candidateOtherInfoDTO = candidateDTO.getCandidateOtherInfoDTO();
        if ( candidateOtherInfoDTO == null ) {
            return null;
        }
        Boolean searchable = candidateOtherInfoDTO.getSearchable();
        if ( searchable == null ) {
            return null;
        }
        return searchable;
    }

    private String candidateDTOCandidateOtherInfoDTOCV(CandidateDTO candidateDTO) {
        if ( candidateDTO == null ) {
            return null;
        }
        CandidateOtherInfoDTO candidateOtherInfoDTO = candidateDTO.getCandidateOtherInfoDTO();
        if ( candidateOtherInfoDTO == null ) {
            return null;
        }
        String cV = candidateOtherInfoDTO.getCV();
        if ( cV == null ) {
            return null;
        }
        return cV;
    }

    private String candidateDTOCandidateOtherInfoDTOReferenceLetter(CandidateDTO candidateDTO) {
        if ( candidateDTO == null ) {
            return null;
        }
        CandidateOtherInfoDTO candidateOtherInfoDTO = candidateDTO.getCandidateOtherInfoDTO();
        if ( candidateOtherInfoDTO == null ) {
            return null;
        }
        String referenceLetter = candidateOtherInfoDTO.getReferenceLetter();
        if ( referenceLetter == null ) {
            return null;
        }
        return referenceLetter;
    }

    private UniversityDTO candidateDTOCandidateOtherInfoDTOUniversityDTO(CandidateDTO candidateDTO) {
        if ( candidateDTO == null ) {
            return null;
        }
        CandidateOtherInfoDTO candidateOtherInfoDTO = candidateDTO.getCandidateOtherInfoDTO();
        if ( candidateOtherInfoDTO == null ) {
            return null;
        }
        UniversityDTO universityDTO = candidateOtherInfoDTO.getUniversityDTO();
        if ( universityDTO == null ) {
            return null;
        }
        return universityDTO;
    }

    private String candidateDTOCandidateOtherInfoDTODesiredJob(CandidateDTO candidateDTO) {
        if ( candidateDTO == null ) {
            return null;
        }
        CandidateOtherInfoDTO candidateOtherInfoDTO = candidateDTO.getCandidateOtherInfoDTO();
        if ( candidateOtherInfoDTO == null ) {
            return null;
        }
        String desiredJob = candidateOtherInfoDTO.getDesiredJob();
        if ( desiredJob == null ) {
            return null;
        }
        return desiredJob;
    }

    private String candidateDTOCandidateOtherInfoDTODesiredWorkingProvince(CandidateDTO candidateDTO) {
        if ( candidateDTO == null ) {
            return null;
        }
        CandidateOtherInfoDTO candidateOtherInfoDTO = candidateDTO.getCandidateOtherInfoDTO();
        if ( candidateOtherInfoDTO == null ) {
            return null;
        }
        String desiredWorkingProvince = candidateOtherInfoDTO.getDesiredWorkingProvince();
        if ( desiredWorkingProvince == null ) {
            return null;
        }
        return desiredWorkingProvince;
    }
}
