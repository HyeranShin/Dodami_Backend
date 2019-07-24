package com.soma.dodam.dodami.web;

import com.soma.dodam.dodami.auth.Auth;
import com.soma.dodam.dodami.auth.AuthAspect;
import com.soma.dodam.dodami.domain.User;
import com.soma.dodam.dodami.dto.ExceptionDto;
import com.soma.dodam.dodami.dto.request.VoiceModelReqDto;
import com.soma.dodam.dodami.dto.response.VoiceModelResDto;
import com.soma.dodam.dodami.service.VoiceModelService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Api(description = "음성 모델 REST API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/voice-model")
public class VoiceModelController {

    private final VoiceModelService voiceModelService;

    @ApiOperation(value = "음성 모델 추가")
    @ApiImplicitParam(name = "Authorization", value = "JWT Token", required = true, dataType = "string", paramType = "header")
    @ApiResponses({
            @ApiResponse(code = 201, message = "음성 모델 추가 성공"),
            @ApiResponse(code = 400, message = "음성 모델 추가 실패", response = ExceptionDto.class),
            @ApiResponse(code = 401, message = "권한 없음", response = ExceptionDto.class),
            @ApiResponse(code = 500, message = "내부 서버 에러")
    })
    @Auth
    @PostMapping("")
    public ResponseEntity<Void> addVoiceModel(HttpServletRequest httpServletRequest,
                                        @RequestBody VoiceModelReqDto voiceModelReqDto) {
        User user = (User)httpServletRequest.getAttribute(AuthAspect.USER_KEY);
        voiceModelReqDto.setUserIdx(user.getIdx());
        voiceModelService.addVoiceModel(voiceModelReqDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "음성 모델 조회")
    @ApiImplicitParam(name = "Authorization", value = "JWT Token", required = true, dataType = "string", paramType = "header")
    @ApiResponses({
            @ApiResponse(code = 200, message = "음성 모델 조회 성공"),
            @ApiResponse(code = 401, message = "권한 없음", response = ExceptionDto.class),
            @ApiResponse(code = 500, message = "내부 서버 에러")
    })
    @Auth
    @GetMapping("")
    public ResponseEntity<List<VoiceModelResDto>> getVoiceModelList(HttpServletRequest httpServletRequest) {
        User user = (User)httpServletRequest.getAttribute(AuthAspect.USER_KEY);
        return ResponseEntity.ok().body(voiceModelService.getVoiceModelList(user));
    }
}
