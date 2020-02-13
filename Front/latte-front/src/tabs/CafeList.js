import React from "react";
import {
    Row,
    Col,
    Input,
    Divider,
    List,
    Card,
    Rate,
    Select,
    Form,
    Button
} from 'antd';
import 'antd/dist/antd.css';
import { Link } from "react-router-dom";
import axios from 'axios';

const { Option } = Select;

class CafeList extends React.Component {
    state = {}
    
    componentDidMount(){
        const base_url = process.env.REACT_APP_SERVER_IP
        axios.get(base_url + '/cafe/all')
            .then(response =>{
            this.setState({
                cafeList: response.data
            });
            });
        };

    handleSearch = e => {
        e.preventDefault();
        this.props.form.validateFields((err, values) => {
            if (!err) {
                console.log('Received values of form: ', values);
            }
        });
    };

    render() {
        if (this.state.cafeList === undefined) {
            return null;
        }
        const {cafeList} = this.state;
        return (
            <Row>
                <Col span={1} />
                <Col span={22}>
                    <Row>
                        <Col span={8} />
                        <Col span={8} >
                            <Form layout="inline" onSubmit={this.handleSearch}>
                                <Form.Item>
                                    <Select style={{ width: '30%', marginRight: '3%' }}>
                                        {/* 옵션 선택 -> 바꾸기 */}
                                        <Option value="1">인기순</Option>
                                        <Option value="2">평점순</Option>
                                        <Option value="3">리뷰 많은 순</Option>
                                    </Select>
                                    <Input type="text" style={{ width: '40%', marginRight: '3%' }} />
                                    <Button type="primary" htmlType="submit">검색</Button>
                                </Form.Item>
                            </Form>
                        </Col>
                        <Col span={8} />
                    </Row>
                    <Divider orientation="left">현재 운영중인 카페</Divider>
                    
                    <List
                        itemLayout="vertical"
                        size="large"
                        pagination={{
                            pageSize: 10
                        }}
                        grid={{ gutter: 36, column: 2 }}
                        dataSource={cafeList}
                        
                        renderItem={ cafe =>(
                            <Link to={{
                                pathname:'/latte/cafedetail',
                                cafe:cafe,
                            }}>
                            <List.Item
                                key={cafe.ccid}>
                                <Card cover={
                                    <img
                                        alt={cafe.cname}
                                        src={cafe.cpic}
                                    />
                                }>
                                    <List.Item.Meta
                                        title={cafe.cname}
                                    />
                                    위치 : {cafe.cloc}
                                    <br/>
                                    평점 : <Rate disabled allowHalf defaultValue={2.5} />             
                                </Card>
                            </List.Item>
                            </Link> 
                        )}
                    />
                </Col>
                <Col span={8} />
            </Row>
        );
    }
}

export default CafeList;