import React, { useState } from "react";
import { Button, Modal, Form } from 'react-bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';
import ReactFullpage from "@fullpage/react-fullpage";
import "../styles.css";
// import NavbarVisitor from "../headers/NavbarVisitor";


const FullpageWrapper = () => {
  // render() {
      const [show, setShow] = useState(false);
      const handleClose = () => setShow(false);
      // const handleShow = () => setShow(true);
      console.log('fullpage에도 렌더링이 되는거야?')
      return (
          <>
              <div className="top-fixed">
                {/* 로그인 하지 않은 상태에서의 헤더 나타내기 */}
                {/* <NavbarVisitor></NavbarVisitor> */}

                <Modal show={show} onHide={handleClose}>
                    <Modal.Header closeButton>
                        <Modal.Title>로그인</Modal.Title>
                    </Modal.Header>
                    <Modal.Body>
                        <Form>
                            <Form.Group>
                                <Form.Label>아이디</Form.Label>
                                <Form.Control type="text" placeholder="아이디" />
                            </Form.Group>
                            <Form.Group>
                                <Form.Label>비밀번호</Form.Label>
                                <Form.Control type="password" placeholder="비밀번호" />
                            </Form.Group>
                        </Form>
                    </Modal.Body>
                    <Modal.Footer>
                        <Button variant="outline-secondary" onClick={handleClose}>취소</Button>
                        <Button vaiant="light" onClick={handleClose}>로그인</Button>
                    </Modal.Footer>
                </Modal>
              </div>
              
              <ReactFullpage
                  navigation
                  scrollOverflow={true}
                  sectionsColor={["#F8C027", "brown", "green"]}
                  licenseKey='OPEN-SOURCE-GPLV3-LICENSE'
                  render={({ fullpageApi }) => {
                      return (
                          <div id="fullpage-wrapper">
                              <div className="section section1">
                                  <h3>라떼는 말이야1</h3>
                              </div>
                              <div className="section">
                                  <h3>라떼는 말이야2</h3>
                              </div>
                              <div className="section">
                                  <h3>라떼는 말이야3</h3>
                                  <button onClick={() => fullpageApi.moveTo(1, 0)}>
                                      Move top
                              </button>
                              </div>
                          </div>
                      );
                  }}
              />
          </>
      );
}

export default FullpageWrapper;