import React from "react";
import {
    Collapse,
    Row,
    Col,
    Form,
    Input,
    Button,
    List,
    Modal,
    InputNumber,
    Select,
    Checkbox
} from 'antd';
import 'antd/dist/antd.css';
import { Card } from 'react-bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';


const { Panel } = Collapse;
const { Option } = Select;

const text = `
  A dog is a type of domesticated animal.
  Known for its loyalty and faithfulness,
  it can be found as a welcome guest in many households across the world.
`;

class Order extends React.Component {

    state = { visible: false };

    // 메뉴 번호 같이 넘겨줘야 함 -> 수정!!!!!!!!
    showModal = () => {
        this.setState({
            visible: true
        });
    };

    handleOk = e => {
        this.setState({
            visible: false,
        });
    };

    handleCancel = e => {
        this.setState({
            visible: false,
        });
    };


    render() {

        const menuList = [
            {
                mmid: '1',
                mname: '딸기 쥬스1',
                mprice: '3000',
                mpic: 'https://previews.123rf.com/images/violetkaipa/violetkaipa1110/violetkaipa111000160/10850112-%EB%94%B8%EA%B8%B0-%EC%A5%AC%EC%8A%A4.jpg'
            },
            {
                mmid: '1',
                mname: '딸기 쥬스2',
                mprice: '3000',
                mpic: 'https://previews.123rf.com/images/violetkaipa/violetkaipa1110/violetkaipa111000160/10850112-%EB%94%B8%EA%B8%B0-%EC%A5%AC%EC%8A%A4.jpg'
            },
            {
                mmid: '1',
                mname: '딸기 쥬스3',
                mprice: '3000',
                mpic: 'https://previews.123rf.com/images/violetkaipa/violetkaipa1110/violetkaipa111000160/10850112-%EB%94%B8%EA%B8%B0-%EC%A5%AC%EC%8A%A4.jpg'
            },
            {
                mmid: '1',
                mname: '딸기 쥬스4',
                mprice: '3000',
                mpic: 'https://previews.123rf.com/images/violetkaipa/violetkaipa1110/violetkaipa111000160/10850112-%EB%94%B8%EA%B8%B0-%EC%A5%AC%EC%8A%A4.jpg'
            },
            {
                mmid: '1',
                mname: '딸기 쥬스5',
                mprice: '3000',
                mpic: 'https://previews.123rf.com/images/violetkaipa/violetkaipa1110/violetkaipa111000160/10850112-%EB%94%B8%EA%B8%B0-%EC%A5%AC%EC%8A%A4.jpg'
            },
            {
                mmid: '1',
                mname: '딸기 쥬스6',
                mprice: '3000',
                mpic: 'https://previews.123rf.com/images/violetkaipa/violetkaipa1110/violetkaipa111000160/10850112-%EB%94%B8%EA%B8%B0-%EC%A5%AC%EC%8A%A4.jpg'
            },
            {
                mmid: '1',
                mname: '딸기 쥬스7',
                mprice: '3000',
                mpic: 'https://previews.123rf.com/images/violetkaipa/violetkaipa1110/violetkaipa111000160/10850112-%EB%94%B8%EA%B8%B0-%EC%A5%AC%EC%8A%A4.jpg'
            },
            {
                mmid: '1',
                mname: '딸기 쥬스8',
                mprice: '3000',
                mpic: 'https://previews.123rf.com/images/violetkaipa/violetkaipa1110/violetkaipa111000160/10850112-%EB%94%B8%EA%B8%B0-%EC%A5%AC%EC%8A%A4.jpg'
            },
            {
                mmid: '1',
                mname: '딸기 쥬스9',
                mprice: '3000',
                mpic: 'https://previews.123rf.com/images/violetkaipa/violetkaipa1110/violetkaipa111000160/10850112-%EB%94%B8%EA%B8%B0-%EC%A5%AC%EC%8A%A4.jpg'
            },
            {
                mmid: '1',
                mname: '딸기 쥬스10',
                mprice: '3000',
                mpic: 'https://previews.123rf.com/images/violetkaipa/violetkaipa1110/violetkaipa111000160/10850112-%EB%94%B8%EA%B8%B0-%EC%A5%AC%EC%8A%A4.jpg'
            },
            {
                mmid: '1',
                mname: '딸기 쥬스11',
                mprice: '3000',
                mpic: 'https://previews.123rf.com/images/violetkaipa/violetkaipa1110/violetkaipa111000160/10850112-%EB%94%B8%EA%B8%B0-%EC%A5%AC%EC%8A%A4.jpg'
            }
        ];

        // 옵션 받아와야 함 -> 수정하기
        const options = [
            { label: '헤이즐넛 시럽 추가', value: '1' },
            { label: '샷 추가', value: '2' },
            { label: '휘핑 추가', value: '3' },
        ];


        return (
            <Row>
                <Col span={1} />
                <Col span={22}>
                    <Row>
                        <Col span={8} />
                        <Col span={8} >
                            <Form layout="inline" onSubmit={this.handleSearch}>
                                <Form.Item>
                                    <Input type="text" style={{ width: '70%', marginRight: '3%' }} />
                                    <Button type="primary" htmlType="submit">검색</Button>
                                </Form.Item>
                            </Form>
                        </Col>
                        <Col span={8} />
                    </Row>
                    <Collapse accordion defaultActiveKey={['1']}>
                        <Panel header="모든 메뉴" key="1">
                            <List
                                itemLayout="vertical"
                                size="large"
                                grid={{ column: 3 }}
                                dataSource={menuList}
                                renderItem={menu => (
                                    <List.Item
                                        key={menu.mmid}>
                                        <Card style={{ width: '70%', textAlign: 'center' }} onClick={this.showModal}>
                                            <Card.Img variant="top" src="https://previews.123rf.com/images/violetkaipa/violetkaipa1110/violetkaipa111000160/10850112-%EB%94%B8%EA%B8%B0-%EC%A5%AC%EC%8A%A4.jpg" />
                                            <Card.Body>
                                                <Card.Text>
                                                    {menu.mname}
                                                </Card.Text>
                                                {/* <Button variant="primary" size="sm">장바구니 담기</Button> */}
                                            </Card.Body>

                                        </Card>
                                    </List.Item>
                                )}
                            />
                        </Panel>
                        <Panel header="커피" key="2">
                            <p>{text}</p>
                        </Panel>
                        <Panel header="쥬스/스무디" key="3">
                            <p>{text}</p>
                        </Panel>
                        <Panel header="차" key="4">
                            <p>{text}</p>
                        </Panel>

                    </Collapse>
                    <Modal
                        title="메뉴 이름"
                        visible={this.state.visible}
                        onOk={this.handleOk}
                        onCancel={this.handleCancel}
                        okText="장바구니 담기"
                        cancelText="취소"
                    >
                        <div style={{ textAlign: 'center' }}>
                            <img src="https://previews.123rf.com/images/violetkaipa/violetkaipa1110/violetkaipa111000160/10850112-%EB%94%B8%EA%B8%B0-%EC%A5%AC%EC%8A%A4.jpg" alt="메뉴이미지" width="70%" />
                        </div>
                        <Form labelCol={{ span: 5 }} wrapperCol={{ span: 12 }}>
                            <Form.Item label="수량">
                                <InputNumber id="quan" min={1} max={10} defaultValue={1} />
                            </Form.Item>
                            <Form.Item label="얼음">
                                <Select>
                                    <Option value="1">기본</Option>
                                    <Option value="2">얼음많이</Option>
                                    <Option value="3">얼음적게</Option>
                                </Select>
                            </Form.Item>
                            <Form.Item label="옵션">
                                <Checkbox.Group options={options} />
                            </Form.Item>
                        </Form>
                    </Modal>
                    <br />
                    <div style={{ textAlign: 'center' }}>
                        <Button>초기화</Button>{'   '}
                        <Button type="primary">장바구니로 가기</Button>
                    </div>

                </Col>
                <Col span={1} />
            </Row>
        );
    }
}

export default Order;
