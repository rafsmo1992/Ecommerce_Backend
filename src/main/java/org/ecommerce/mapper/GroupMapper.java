package org.ecommerce.mapper;

import org.ecommerce.domain.Group;
import org.ecommerce.domain.dto.GroupDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroupMapper {

    public Group mapToGroup(final GroupDto groupDto) {

        return new Group(groupDto.getName());
    }

    public GroupDto mapToGroupDto(final Group group) {
        return new GroupDto(
                group.getId(),
                group.getName()
        );
    }

    public Group mapToUpdatedGroup(final GroupDto groupDto) {
        return new Group(
                groupDto.getId(),
                groupDto.getName()
        );
    }
    public List<GroupDto> mapToGroupDtoList(final List<Group> groupList) {
        return groupList.stream()
                .map(this::mapToGroupDto)
                .collect(Collectors.toList());
    }
}
