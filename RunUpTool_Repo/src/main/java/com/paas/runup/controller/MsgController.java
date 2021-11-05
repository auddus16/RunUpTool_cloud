package com.paas.runup.controller;

import java.net.Socket;

import javax.websocket.server.ServerEndpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.paas.runup.dto.MsgRoom;
import com.paas.runup.service.MsgService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@ServerEndpoint("/websocket")
@RequestMapping("/room")
public class MsgController extends Socket{
	
	//private static final List<Session> session = new ArrayList<Session>();
	@Autowired
	private MsgService msgService;
	
	@ApiOperation(value="선생님 - 수업 열기(실시간 도구창 열기)", notes="시작할 수업의 c_no를 roomId로 하여 실시간 도구창을 생성한다.")
	@ApiImplicitParam(name="roomId", value="roomId(=c_no)", example = "1")
	@RequestMapping(value="/createRoom", method=RequestMethod.POST)
	public void createRoom(@RequestParam String roomId) {
		
		MsgRoom msgRoom = new MsgRoom(roomId);
		
		if(msgService.insertMsgRoom(msgRoom)==0) {
			log.info("방 생성 실패");
		}
		else {
			log.info("방 만들어졌습니다!!");
			//message= new Messsage(ENTER, );
			//msgRoom.handleActions(new WebSocketSession(), message, msgService);
		}
	}

	@ApiOperation(value="선생님 - 수업 닫기(실시간 도구창 닫기)", notes="종료할 수업의 c_no를 roomId로 하여 실시간 도구창을 종료한다.")
	@ApiImplicitParam(name="roomId", value="roomId(=c_no)", example = "1")
	@RequestMapping(value="/deleteRoom", method= {RequestMethod.DELETE})
	public void deleteRoom(@RequestParam String roomId) {
				
		if(msgService.deleteMsgRoom(roomId)==0) {
			log.info("방 삭제 실패");
		}
		else {
			log.info("방 삭제했습니다!!");
			//message= new Messsage(ENTER, );
			//msgRoom.handleActions(new WebSocketSession(), message, msgService);
		}
	}
	
	
//	@GetMapping("/findAllRoom")
//	public List<MsgRoom> findAllRoom() {
//		return msgService.findAllRoom();
//	}
	
//	@GetMapping("/")
//	public String index() {
//		return "index.html";
//	}
	
//	@OnOpen
//    public void open(Session newUser) {
//        System.out.println("connected");
//        session.add(newUser);
//        System.out.println(newUser.getId());
//    }
//
//    @OnMessage
//    public void getMsg(Session recieveSession, String msg) {
//        for (int i = 0; i < session.size(); i++) {
//            if (!recieveSession.getId().equals(session.get(i).getId())) {
//                try {
//                    session.get(i).getBasicRemote().sendText("상대 : "+msg);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }else{
//                try {
//                    session.get(i).getBasicRemote().sendText("나 : "+msg);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }

}
