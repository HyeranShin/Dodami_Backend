package com.soma.dodam.dodami.web;

import com.soma.dodam.dodami.auth.Auth;
import com.soma.dodam.dodami.auth.AuthAspect;
import com.soma.dodam.dodami.domain.User;
import com.soma.dodam.dodami.dto.ExceptionDto;
import com.soma.dodam.dodami.dto.request.PlayedTimeReqDto;
import com.soma.dodam.dodami.dto.response.PlayingStoryResDto;
import com.soma.dodam.dodami.service.PlayingStoryService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Api(description = "플레이중인 스토리 REST API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/playing")
public class PlayingStoryController {

    private final PlayingStoryService playingStoryService;

    @ApiOperation(value = "플레이중인 스토리 조회")
    @ApiImplicitParam(name = "Authorization", value = "JWT Token", required = true, dataType = "string", paramType = "header")
    @ApiResponses({
            @ApiResponse(code = 200, message = "플레이중인 스토리 조회 성공"),
            @ApiResponse(code = 204, message = "플레이중인 스토리 조회 결과 없음", response = Object.class),
            @ApiResponse(code = 401, message = "권한 없음", response = ExceptionDto.class),
            @ApiResponse(code = 500, message = "내부 서버 에러")
    })
    @Auth
    @GetMapping("")
    public ResponseEntity<List<PlayingStoryResDto>> getPlayingList(HttpServletRequest httpServletRequest) {
        User user = (User)httpServletRequest.getAttribute(AuthAspect.USER_KEY);
        return ResponseEntity.ok().body(playingStoryService.getPlayingList(user.getIdx()));
    }

    @ApiOperation(value = "재생 시간 변경")
    @ApiImplicitParam(name = "Authorization", value = "JWT Token", required = true, dataType = "string", paramType = "header")
    @ApiResponses({
            @ApiResponse(code = 200, message = "재생 시간 변경 성공"),
            @ApiResponse(code = 400, message = "재생 시간 변경 실패", response = ExceptionDto.class),
            @ApiResponse(code = 401, message = "권한 없음", response = ExceptionDto.class),
            @ApiResponse(code = 500, message = "내부 서버 에러")
    })
    @Auth
    @PostMapping("/{contentsIdx}")
    public ResponseEntity<Void> updatePlayedTime(HttpServletRequest httpServletRequest,
                                               @PathVariable Long contentsIdx,
                                               @RequestBody PlayedTimeReqDto playedTimeReqDto) {
        User user = (User)httpServletRequest.getAttribute(AuthAspect.USER_KEY);
        playingStoryService.updatePlayedTime(user.getIdx(), contentsIdx, playedTimeReqDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @ApiOperation(value = "재생 완료")
    @ApiImplicitParam(name = "Authorization", value = "JWT Token", required = true, dataType = "string", paramType = "header")
    @ApiResponses({
            @ApiResponse(code = 200, message = "재생 완료 성공"),
            @ApiResponse(code = 400, message = "재생 완료 실패", response = ExceptionDto.class),
            @ApiResponse(code = 401, message = "권한 없음", response = ExceptionDto.class),
            @ApiResponse(code = 500, message = "내부 서버 에러")
    })
    @Auth
    @DeleteMapping("/{contentsIdx}")
    public ResponseEntity<Void> completeStory(HttpServletRequest httpServletRequest,
                                              @PathVariable Long contentsIdx) {
        User user = (User)httpServletRequest.getAttribute(AuthAspect.USER_KEY);
        playingStoryService.completeStory(user.getIdx(), contentsIdx);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
