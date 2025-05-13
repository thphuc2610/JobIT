package com.r2s.findInternship.infracstructure.persistence.mapper;

import com.r2s.findInternship.application.dto.user.UserCreationDTO;
import com.r2s.findInternship.application.dto.user.UserDTO;
import com.r2s.findInternship.application.dto.user.UserProfileDTO;
import com.r2s.findInternship.domain.entity.User;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-10T12:54:19+0700",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.11 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private StatusMapper statusMapper;

    @Override
    public User toEntity(UserCreationDTO creationDTO) {
        if ( creationDTO == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.email( creationDTO.getEmail() );
        user.gender( creationDTO.getGender() );
        user.avatar( creationDTO.getAvatar() );
        user.phone( creationDTO.getPhone() );
        user.firstName( creationDTO.getFirstName() );
        user.lastName( creationDTO.getLastName() );
        user.birthDay( creationDTO.getBirthDay() );
        user.city( creationDTO.getCity() );
        user.district( creationDTO.getDistrict() );
        user.location( creationDTO.getLocation() );
        if ( creationDTO.getMailReceive() != null ) {
            user.mailReceive( creationDTO.getMailReceive() );
        }

        return user.build();
    }

    @Override
    public User toEntity(UserProfileDTO userProfileDTO) {
        if ( userProfileDTO == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        if ( userProfileDTO.getMailReceive() != null ) {
            user.mailReceive( userProfileDTO.getMailReceive() );
        }
        user.city( userProfileDTO.getCity() );
        user.district( userProfileDTO.getDistrict() );
        user.email( userProfileDTO.getEmail() );
        user.gender( userProfileDTO.getGender() );
        user.avatar( userProfileDTO.getAvatar() );
        user.phone( userProfileDTO.getPhone() );
        user.firstName( userProfileDTO.getFirstName() );
        user.lastName( userProfileDTO.getLastName() );
        user.birthDay( userProfileDTO.getBirthDay() );
        user.location( userProfileDTO.getLocation() );

        return user.build();
    }

    @Override
    public UserDTO toDTO(User user) {
        if ( user == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setRoleDTO( roleMapper.toDTO( user.getRole() ) );
        userDTO.setStatusDTO( statusMapper.toDTO( user.getStatus() ) );
        userDTO.setMailReceive( user.isMailReceive() );
        userDTO.setEmail( user.getEmail() );
        userDTO.setFirstName( user.getFirstName() );
        userDTO.setLastName( user.getLastName() );
        userDTO.setGender( user.getGender() );
        userDTO.setBirthDay( user.getBirthDay() );
        userDTO.setPhone( user.getPhone() );
        userDTO.setAvatar( user.getAvatar() );
        userDTO.setCity( user.getCity() );
        userDTO.setDistrict( user.getDistrict() );
        userDTO.setLocation( user.getLocation() );
        userDTO.setId( user.getId() );

        return userDTO;
    }

    @Override
    public User toEntity(UserDTO userDTO) {
        if ( userDTO == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.role( roleMapper.toEntity( userDTO.getRoleDTO() ) );
        user.status( statusMapper.toEntity( userDTO.getStatusDTO() ) );
        if ( userDTO.getMailReceive() != null ) {
            user.mailReceive( userDTO.getMailReceive() );
        }
        if ( userDTO.getId() != null ) {
            user.id( userDTO.getId() );
        }
        user.email( userDTO.getEmail() );
        user.gender( userDTO.getGender() );
        user.avatar( userDTO.getAvatar() );
        user.phone( userDTO.getPhone() );
        user.firstName( userDTO.getFirstName() );
        user.lastName( userDTO.getLastName() );
        user.birthDay( userDTO.getBirthDay() );
        user.city( userDTO.getCity() );
        user.district( userDTO.getDistrict() );
        user.location( userDTO.getLocation() );

        return user.build();
    }
}
