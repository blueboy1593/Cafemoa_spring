import React, { Component, useState } from 'react'
import { Button, Modal, Form } from 'react-bootstrap';

export default class Order extends Component {
  render() {
    const cafe = this.props.location.state.cafe
    if (cafe !== undefined) {
      const menus = cafe.menus;
      return (
        <div>
          <center>
          <h1>{ cafe.cname } 카페의 주문하기 페이지</h1>
            {menus.map(menu => (
              <CafeDetailCard
                menu = {menu}
                key = {menu.mmid}
              />
            ))}
          </center>
        </div>
      )
    }
    else {
      this.props.history.push("/");
      return
    }
 }    
}

const CafeDetailCard = (props) => {
  const [show, setShow] = useState(false);
  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);

  const menu = props.menu
  return(
    <div style={{display:"inline-flex", padding:"20px", width:'20rem', margin:"10px"}}>
      <div className="card" style={{width: '18rem'}}>
        <img src={menu.mpic} className="card-img-top" alt="..."/>
        <div className="card-body">
          <p className="card-text">이름: {menu.mname}</p>
          <p className="card-text">기본 옵션 가격: {menu.menuSize[0].msprice} 원</p>
          <Button onClick={handleShow} variant="outline-info">옵션 선택</Button>
        </div>
      </div>
      <Modal show={show} onHide={handleClose}>
        <Modal.Header closeButton>
          <Modal.Title>옵션 선택</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <img src={menu.mpic} className="card-img-top" alt="..."/>
          <Form>
              <Form.Group>
                <Form.Label>사이즈</Form.Label>
                <Form.Control as="select" placeholder="사이즈 선택">
                  <option>S</option>
                  <option>M</option>
                  <option>L</option>
                </Form.Control>
              </Form.Group>
              <Form.Group>
                <Form.Label>얼음</Form.Label>
                {/* <Form.Control type="password" placeholder="비밀번호" /> */}
                <Form.Control as="select" placeholder="얼음량 선택">
                  <option>기본</option>
                  <option>얼음 많이</option>
                  <option>얼음 적게</option>
                </Form.Control>
              </Form.Group>
          </Form>
        </Modal.Body>
        <Modal.Footer>
          <Button variant="outline-secondary" onClick={handleClose}>취소</Button>
          <Button vaiant="light" onClick={handleClose}>장바구니 담기</Button>
        </Modal.Footer>
      </Modal>
    </div>
  );
};