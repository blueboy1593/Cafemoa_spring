import React from "react";
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import * as serviceWorker from './serviceWorker';
import {Provider} from 'react-redux';
import store from './store';
import { BrowserRouter } from 'react-router-dom';
// store에서 직접 router를 써보기 위해서 이런 짓을 해본다.
// 아래 코드는 부트스트랩을 전체 지역에서 사용하기 위해서 가져온 코드.
import 'bootstrap/dist/css/bootstrap.min.css';

// import { Navbar, Nav, Button, Modal, Form } from 'react-bootstrap';
// import 'bootstrap/dist/css/bootstrap.min.css';
// import ReactFullpage from "@fullpage/react-fullpage";

// import "./styles.css";

// const FullpageWrapper = () => {
//     // render() {
//         const [show, setShow] = useState(false);
//         const handleClose = () => setShow(false);
//         const handleShow = () => setShow(true);

//         return (
//             <>
//                 <div className="top-fixed">
//                     <Navbar bg="fade" expand="lg">
//                         <Navbar.Brand href="#home">
//                             <img
//                                 alt=""
//                                 src="/logo_text.png"
//                                 className="d-inline-block align-top"
//                             />{' '}
//                         </Navbar.Brand>
//                         <Navbar.Toggle aria-controls="basic-navbar-nav" />
//                         <Navbar.Collapse id="basic-navbar-nav">
//                             <Nav className="mr-auto">
//                                 <Nav.Link href="#home"><img src="/cafeInfo.png" alt="" /></Nav.Link>
//                             </Nav>
//                             <Nav>
//                                 <Nav.Link onClick={handleShow}><img src="/login.png" alt="" /></Nav.Link>
//                                 <Nav.Link><img src="/signup.png" alt="" /></Nav.Link>
//                             </Nav>
//                         </Navbar.Collapse>
//                     </Navbar>
//                     <Modal show={show} onHide={handleClose}>
//                         <Modal.Header closeButton>
//                             <Modal.Title>로그인</Modal.Title>
//                         </Modal.Header>
//                         <Modal.Body>
//                             <Form>
//                                 <Form.Group>
//                                     <Form.Label>아이디</Form.Label>
//                                     <Form.Control type="text" placeholder="아이디" />
//                                 </Form.Group>
//                                 <Form.Group>
//                                     <Form.Label>비밀번호</Form.Label>
//                                     <Form.Control type="password" placeholder="비밀번호" />
//                                 </Form.Group>
//                             </Form>
//                         </Modal.Body>
//                         <Modal.Footer>
//                             <Button variant="outline-secondary" onClick={handleClose}>취소</Button>
//                             <Button vaiant="light" onClick={handleClose}>로그인</Button>
//                         </Modal.Footer>
//                     </Modal>
//                 </div>
//                 <ReactFullpage
//                     navigation
//                     scrollOverflow={true}
//                     sectionsColor={["#F8C027", "brown", "green"]}
//                     licenseKey='OPEN-SOURCE-GPLV3-LICENSE'
//                     render={({ fullpageApi }) => {
//                         return (
//                             <div id="fullpage-wrapper">
//                                 <div className="section section1">
//                                     <h3>라떼는 말이야1</h3>
//                                 </div>
//                                 <div className="section">
//                                     <h3>라떼는 말이야2</h3>
//                                 </div>
//                                 <div className="section">
//                                     <h3>라떼는 말이야3</h3>
//                                     <button onClick={() => fullpageApi.moveTo(1, 0)}>
//                                         Move top
//                                 </button>
//                                 </div>
//                             </div>
//                         );
//                     }}
//                 />
//             </>
//         );
// }


ReactDOM.render(
    // 이 프로바이더를 통해서 store를 일일히 import할 필요가 없어지는 것. Magic이래....;;
    // App을 provider로 감쌈으로서 App 내에 있는 모든 component에서 store에 접근할 수 있을 것.
    <BrowserRouter>
        <Provider store={store}>
            <App />
        </Provider>
    </BrowserRouter>
, document.getElementById('root'));

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: https://bit.ly/CRA-PWA
serviceWorker.unregister();
