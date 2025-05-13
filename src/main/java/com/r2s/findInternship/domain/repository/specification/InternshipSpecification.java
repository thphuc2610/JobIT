package com.r2s.findInternship.domain.repository.specification;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import com.r2s.findInternship.application.dto.InternshipProgrammeFilterDTO;
import com.r2s.findInternship.domain.entity.*;

import org.springframework.data.jpa.domain.Specification;

public final class InternshipSpecification implements Specification<InternshipProgramme> {


    private static final long serialVersionUID = 1L;

    // public static Specification<InternshipProgramme>
    // filter(InternshipProgrammeFilterDTO demandFilterDTO) {
    // return ((root, query, criteriaBuilder) -> {
    // List<Predicate> predicates = new ArrayList<>();
    //
    // Join<InternshipProgramme, InternshipMajor> MajorJoin =
    // root.join("majorDemands", JoinType.LEFT);
    // Join<Partner, InternshipProgramme> demandPartnerJoin = root.join("partner",
    // JoinType.LEFT);
    // Join<Partner, University> PartnerUniversityJoin =
    // demandPartnerJoin.join("university",
    // JoinType.LEFT);
    // query.distinct(true);
    //
    // if (demandFilterDTO.getName()!=null &&
    // !demandFilterDTO.getName().trim().isEmpty()) {
    // String name = demandFilterDTO.getName();
    // predicates.add(criteriaBuilder.like(criteriaBuilder.upper(root.get("name")),
    // "%" + name.toUpperCase() + "%"));
    //
    // }
    ////
    // if (demandFilterDTO.getUniversityTypeIds()!=null &&
    // demandFilterDTO.getUniversityTypeIds().size()>0) {
    // List<String> universityTypeIds = demandFilterDTO.getUniversityTypeIds();
    // predicates.add(getQueryMultipleField("universityType", universityTypeIds,
    // criteriaBuilder, PartnerUniversityJoin));
    // }
    // if (demandFilterDTO.getMajorIds() != null &&
    // demandFilterDTO.getMajorIds().size() > 0) {
    // List<String> majorIds = demandFilterDTO.getMajorIds();
    // predicates.add(getQueryMultipleField("major", majorIds, criteriaBuilder,
    // MajorJoin));
    // }
    //// if (demandFilterDTO.getUniversityId() != null) {
    //// Integer universityId = demandFilterDTO.getUniversityId();
    //// predicates.add(criteriaBuilder.equal(PartnerUniversityJoin.get("university"),
    // universityId));
    //// }
    //
    // return criteriaBuilder.and(predicates.toArray(new
    // jakarta.persistence.criteria.Predicate[predicates.size()]));
    //
    // });
    // }
    static int STATUS_ACTIVE = 1;

//    public static Specification<InternshipProgramme> filter(InternshipProgrammeFilterDTO internshipProgrammeFilterDTO) {
//        return (root, query, cb) -> {
//            List<jakarta.persistence.criteria.Predicate> predicates = new ArrayList<>();
//
//            // add name filter
//            if (internshipProgrammeFilterDTO.getName() != null) {
//                Join<InternshipProgramme, University> universityJoin = root.join("university");
//                predicates.add(cb.or(
//                        cb.like(root.get("title"), "%" + internshipProgrammeFilterDTO.getName() + "%"),
//                        cb.like(root.join("university").get("name"),
//                                "%" + internshipProgrammeFilterDTO.getName() + "%")));
//            }
//
//            // add position filter
//            if (internshipProgrammeFilterDTO.getPositionIds() != null
//                    && !internshipProgrammeFilterDTO.getPositionIds().isEmpty()) {
//                Join<InternshipProgramme, InternshipPosition> positionJoin = root.join("internshipPositions");
//                predicates
//                        .add(positionJoin.get("position").get("id").in(internshipProgrammeFilterDTO.getPositionIds()));
//                query.distinct(true);
//            }
//
//            // add university type filter
//            if (internshipProgrammeFilterDTO.getUniversityTypeIds() != null
//                    && !internshipProgrammeFilterDTO.getUniversityTypeIds().isEmpty()) {
//                Join<InternshipProgramme, University> universityJoin = root.join("university");
//                Join<InternshipProgramme, UniversityType> universityTypeJoin = universityJoin.join("universityType");
//                predicates.add(universityTypeJoin.get("universityType").get("id")
//                        .in(internshipProgrammeFilterDTO.getUniversityTypeIds()));
//                query.distinct(true);
//            }
//
//            // add major filter
//            if (internshipProgrammeFilterDTO.getMajorIds() != null
//                    && !internshipProgrammeFilterDTO.getMajorIds().isEmpty()) {
//                Join<InternshipProgramme, InternshipMajor> majorJoin = root.join("internshipMajors");
//                predicates.add(majorJoin.get("major").get("id").in(internshipProgrammeFilterDTO.getMajorIds()));
//                query.distinct(true);
//            }
//
//            // add location filter
//            if (internshipProgrammeFilterDTO.getLocation() != null) {
//                String modifiedString = internshipProgrammeFilterDTO.getLocation().replaceAll("Thành phố |Tỉnh ", "");
//                predicates.add(cb.like(root.get("location"), "%" + modifiedString + "%"));
//            }
//
//            // add order filter
//            query.orderBy(cb.desc(root.get("createdDate")));
//
//            // add status filter
//            predicates.add(cb.equal(root.get("status"), STATUS_ACTIVE));
//
//            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
//        };
//    }

    private static Predicate getQueryMultipleField(String attribute, List<String> values,
            CriteriaBuilder criteriaBuilder,
            Join<?, ?> join) {

        Predicate nameConditions = null;
        for (String value : values) {
            if (nameConditions == null) {
                nameConditions = criteriaBuilder
                        .or(criteriaBuilder.equal(join.get(attribute), Integer.parseInt(value)));
            } else {
                nameConditions = criteriaBuilder.or(nameConditions,
                        criteriaBuilder.equal(join.get(attribute), Integer.parseInt(value)));
            }
        }
        return (Predicate) nameConditions;
    }

    private static Predicate getQueryProvince(int value, String field, CriteriaBuilder criteriaBuilder,
            Root<InternshipProgramme> root) {

        return (Predicate) criteriaBuilder.equal(root.get(field), value);
    }

    @Override
    public jakarta.persistence.criteria.Predicate toPredicate(Root<InternshipProgramme> root, CriteriaQuery<?> query,
            CriteriaBuilder criteriaBuilder) {
        // TODO Auto-generated method stub
        return null;
    }
}