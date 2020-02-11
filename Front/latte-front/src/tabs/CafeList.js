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

const { Option } = Select;

class CafeList extends React.Component {
        state = {
            cafeList: [
            // 이부분은 axios로 받아오더라도 굳이 지우지 말고 주석으로 남겨놓자.!!
            {
                id:1,
                name: '스타벅스',
                cloc: '역삼동'
            },
            {
                id:2,
                name: '이디야',
                cloc: '강남'
            },
            {
                id:3,
                name: '커피빈',
                cloc: '역삼동'
            },
            {
                id:4,
                name: '스타벅스',
                cloc: '역삼동'
            },
            {
                id:5,
                name: '이수민',
                cloc: '김도하'
            }, {
                id:6,
                name: '김강현',
                cloc: '김시효'
            }, {
                id:7,
                name: '이수민',
                cloc: '윤가영'
            }, {
                id:8,
                name: '스타벅스',
                cloc: '역삼동'
            }, {
                id:9,
                name: '스타벅스',
                cloc: '역삼동'
            },
        ]
    }

    handleSearch = e => {
        e.preventDefault();
        this.props.form.validateFields((err, values) => {
            if (!err) {
                console.log('Received values of form: ', values);
            }
        });
    };
        // componentDidMount(){
        // axios.get('http://i02a301.p.ssafy.io:8080/latte/cafe/all')
        //     .then(response =>{
        //     this.setState({
        //         cafes:response.data
        //     });
        //     // console.log(this.state);
        //     });


        // };
        // 일단 이 부분은 해보려다가 실패.
        // axios_url.get('latte/cafe/all')

    render() {
        const {cafeList} = this.state;

        console.log(this.state.cafeList)
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
                            <Link to='visitor/cafedetail'>
                            <List.Item
                                key={cafe.cname}>
                                <Card cover={
                                    <img
                                        alt="example"
                                        src="https://www.jeongdong.or.kr/static/portal/img/HKPU_04_04_pic3.jpg"
                                    />
                                }>
                                    <List.Item.Meta
                                        title={cafe.name}
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


// import React, { Component } from 'react'
// import CafeInfo from '../components/CafeInfo';
// import './CafeList.css';
// import axios from 'axios';


// export default class CafeList extends Component {
//     state = {
//         cafes: [
//             // 이부분은 axios로 받아오더라도 굳이 지우지 말고 주석으로 남겨놓자.!!
//             {
//                 ccid: 1,
//                 cname: "Twosome Place",
//                 cpic: "http://www.newsgn.com/imgdata/newsgn_com/201709/2017092554155245.jpg",
//             },
//             {
//                 ccid: 2,
//                 cname: "Starbucks",
//                 cpic: "https://t1.daumcdn.net/cfile/tistory/236B503C5961BE5536",
//             },
//             {
//                 ccid: 3,
//                 cname: "Coffeebean",
//                 cpic: "http://www.mirae-biz.com/news/photo/201803/37567_30810_4718.jpg",
//             },
//             {
//                 ccid: 4,
//                 cname: "Banapresso",
//                 cpic: "http://mblogthumb1.phinf.naver.net/MjAxOTAzMjJfMjM4/MDAxNTUzMjIzNjAwMDU4.jUAy36LFmP88TJk1On4VNol4xYeMPXrEOyMqI83VXOYg.wWNmxoaAUtzUHJ-xSsN48Oif-856lcTlGJCSwBImLE8g.JPEG.lovenonsul/IMG_2529.JPG?type=w800",
//             },
//             {
//                 ccid: 5,
//                 cname: "Idiya Coffee",
//                 cpic: "http://danmee.chosun.com/site/data/img_dir/2017/08/18/2017081801938_0.jpg",
//             },
//             {
//                 ccid: 6,
//                 cname: "Hollys Coffee",
//                 cpic: "http://admin.hollys.co.kr/upload/branch/store_201609210824422010.jpg",
//             },
//         ]
//     }

//     componentDidMount(){
//         const base_url = process.env.REACT_APP_SERVER_IP
//         axios.get(base_url + '/cafe/all')
//             .then(response =>{
//             this.setState({
//                 cafes:response.data
//             });
//             });
//         };

//     render() {
//         const {cafes} = this.state;
//         // console.log(cafes)
//         return (
//             <div>
//                 <br></br>
//                 <div className="cafes">
//                     {cafes.map(cafe => (
//                         <CafeInfo
//                             key={cafe.ccid}
//                             id={cafe.ccid}
//                             name={cafe.cname}
//                             picture={cafe.cpic}
//                             />
//                     ))}
//                 </div>
//             </div>
//         )
//     }
// }

