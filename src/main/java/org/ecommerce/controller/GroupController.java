package org.ecommerce.controller;

import lombok.RequiredArgsConstructor;
import org.ecommerce.domain.Group;
import org.ecommerce.domain.dto.GroupDto;
import org.ecommerce.mapper.GroupMapper;
import org.ecommerce.service.GroupService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ecommercee/groups")
@RequiredArgsConstructor
public class GroupController {
    private final GroupService groupService;
    private final GroupMapper groupMapper;

    @GetMapping
    public ResponseEntity<List<GroupDto>> getAllGroups() {
        List<Group> groups = groupService.getAllGroups();
        return ResponseEntity.ok(groupMapper.mapToGroupDtoList(groups));
    }

    @GetMapping(value = "{groupId}")
    public ResponseEntity<GroupDto> getGroup(@PathVariable Long groupId){
        return new ResponseEntity<>(groupMapper.mapToGroupDto(groupService.getGroup(groupId)), HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createGroup(@RequestBody GroupDto groupDto) {
        Group group = groupMapper.mapToGroup(groupDto);
        groupService.saveGroup(group);
        return ResponseEntity.ok().build();
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GroupDto> updateGroup(@RequestBody GroupDto groupDto){
        return ResponseEntity.ok(groupMapper.mapToGroupDto(groupService.updateGroup(groupMapper.mapToUpdatedGroup(groupDto))));
    }

    @DeleteMapping(value = "{groupId}")
    public ResponseEntity<Void> deleteGroup(@PathVariable Long groupId) {
        groupService.deleteGroup(groupId);
        return ResponseEntity.ok().build();
    }
}
