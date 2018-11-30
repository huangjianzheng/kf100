<template>
    <div class="app-container">
        <span class="tops">企业管理</span><hr>
        <div class="filterDiv">
            <div>
                <div class="enterpriseName">企业名称：</div>
                <span>
                    <el-input placeholder="请输入要查询企业名称" v-model="enterprise" clearable class="inputEnterpriseName" prefix-icon="el-icon-search">
                    </el-input>
                </span>
                <div class="number">
                    主账号：
                </div>
                <span>
                    <el-input placeholder="请输入要查询主账号" v-model="number" clearable class="inputnumber" prefix-icon="el-icon-search">
                    </el-input>
                </span>
                <div class="number">
                    注册邮箱：
                </div>
                <span>
                    <el-input placeholder="请输入要查询邮箱" v-model="email" clearable class="inputnumber" prefix-icon="el-icon-search">
                    </el-input>
                </span>&nbsp;&nbsp;&nbsp;
                <el-button type="primary" plain @click="search">查询</el-button>
            </div>
        </div><br>
        <el-table :data="records" border style="width: 100%" class="tabls">
            <el-table-column prop="id" label="ID" width="100" align="center">
            </el-table-column>
            <el-table-column prop="userName" label="企业名称（姓名）" width="320" align="center">
            </el-table-column>
            <el-table-column prop="account" label="用户名" width="250" align="center">
            </el-table-column>
            <el-table-column prop="accountType" label="账号类型" width="250" align="center">
                <template slot-scope="scope">
                    <span v-if="1===scope.row.accountType">主账号</span>
                    <span v-else>子账号</span>
                </template>
            </el-table-column>
            <el-table-column prop="userType" label="用户类型" width="250" align="center">
                <template slot-scope="scope">
                    <span v-if="1===scope.row.userType">个人</span>
                    <span v-else>企业</span>
                </template>
            </el-table-column>
            <el-table-column prop="createTime" label="注册时间" align="center">
            </el-table-column>
            <el-table-column label="操作" width="160 " align="center">
                <template slot-scope="scope">
                    <label style="cursor: pointer;" class="selectData" @click="selectData(scope.row.id,scope.row.userType)">查看资料</label>
                </template>
            </el-table-column>
        </el-table><br>

        <div class="page-bar">
            <el-pagination class="fr" @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="current" :page-sizes="[5,10,20,30]" :page-size="size" layout="total,  prev, pager, next, sizes,jumper" :total="total">
            </el-pagination>
        </div>

        <el-dialog title="企业资料" :visible.sync="dialogVisible" width="35%" :before-close="handleClose" v-loading="loading">
            <table class="tabs">
                <tr>
                    <th>企业名称</th>
                    <td>{{tableData.companyName}}</td>
                </tr>
                <tr>
                    <th>企业法人姓名</th>
                    <td>{{tableData.corporationName}}</td>
                </tr>
                <tr>
                    <th>公司地址</th>
                    <td>{{tableData.companyAddress}}</td>
                </tr>
                <tr>
                    <th>营业执照编号</th>
                    <td>{{tableData.businessLicenseNumber}}</td>
                </tr>
                <tr class="picture">
                    <th>营业执照扫码件</th>
                    <td>{{tableData.authImage}}</td>
                </tr>
            </table><br><br><br>

            <span class="contact">联系人资料</span><br><br><br>
            <table class="tabs">
                <tr>
                    <th>联系人</th>
                    <td>{{tableData.userName}}</td>
                </tr>
                <tr>
                    <th>联系地址</th>
                    <td>{{tableData.address}}</td>
                </tr>
                <tr>
                    <th>联系邮箱</th>
                    <td>{{tableData.email}}</td>
                </tr>
                <tr>
                    <th>电话</th>
                    <td>{{tableData.cellPhone}}</td>
                </tr>
            </table>

            <span slot="footer" class="dialog-footer">
                <el-button type="primary" @click="closeDialogVisible()">关 闭</el-button>
            </span>
        </el-dialog>

        <el-dialog title="个人资料" :visible.sync="personageDialogVisible" width="35%" :before-close="handleClose">
            <table class="tabs">
                <tr>
                    <th>姓名</th>
                    <td>{{tableData.userName}}</td>
                </tr>
                <tr>
                    <th>性别</th>
                    <td>
                        <span v-if="tableData.sex == 0 ">女</span>
                        <span v-else>男</span>
                    </td>
                </tr>
                <tr>
                    <th>身份证号码</th>
                    <td>{{tableData.identificationNumber}}</td>
                </tr>
                <tr>
                    <th>单位</th>
                    <td>{{tableData.companyName}}</td>
                </tr>
                <tr>
                    <th>联系地址</th>
                    <td>{{tableData.address}}</td>
                </tr>
                <tr>
                    <th>电子邮箱</th>
                    <td>{{tableData.email}}</td>
                </tr>
                <tr>
                    <th>手机号码</th>
                    <td>{{tableData.cellPhone}}</td>
                </tr>
                <tr>
                    <th>账号所属</th>
                    <td>
                        <span v-if="tableData.masterCompanyName == null ">
                            无归属
                        </span>
                        <span v-else>{{tableData.masterCompanyName}}</span>
                    </td>
                </tr>
            </table>
            <span slot="footer" class="dialog-footer">
                <!-- <el-button @click="personageDialogVisible = false">取 消</el-button> -->
                <el-button type="primary" @click="closepersonageDialogVisible">关 闭</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
import { getAllUser, getUserCompany, getUserInfo } from "@/api/user";
export default {
    data() {
        return {
            uploadImageUrl: process.env.UPLOAD_IMAGE_URL,
            imageTypes: process.env.IMAGE_TYPES,
            imageMaxSize: process.env.IMAGE_MAX_SIZE,
            imagePath: process.env.IMAGE_PATH,

            dialogVisible: false,
            personageDialogVisible: false,
            records: [],
            enterprise: "",
            number: "",
            email: "",
            current: 1,
            pageSize: 10, //每页记录数
            total: 0, //总记录数
            tableData: {},
            loading: false
        };
    },
    created() {
        this.getUserList();
    },
    methods: {
        closepersonageDialogVisible() {
            this.personageDialogVisible = false;
            this.tableData = "";
        },
        closeDialogVisible() {
            this.dialogVisible = false;
            this.tableData = "";
        },
        selectData(id, userType) {
            if (userType == 1) {
                this.personageDialogVisible = true;
                this.loading = true;
                getUserInfo(id).then(response => {
                    this.loading = false;
                    this.tableData = response.data;
                });
            }
            if (userType == 2) {
                this.dialogVisible = true;
                this.loading = true;
                getUserCompany(id).then(response => {
                    this.loading = false;
                    this.tableData = response.data;
                });
            }
        },
        search() {
            this.current = 1;
            this.getUserList();
        },
        getUserList() {
            this.loading = true;
            getAllUser(
                this.enterprise,
                this.number,
                this.email,
                this.current,
                this.pageSize
            ).then(response => {
                this.loading = false;
                this.records = response.data.records;
                this.total = response.data.total;
            });
        },
        handleSizeChange(val) {
            console.log(`每页 ${val} 条`);
            this.pageSize = val;
            this.current = 1;
            this.getUserList();
        },
        handleCurrentChange(val) {
            console.log(`当前页: ${val}`);
            this.current = val;
            this.getUserList();
        }
    }
};
</script>

<style scoped>
.contact {
    font-size: 16px;
    color: black;
}
.tops {
    font-size: 26px;
}
.filterDiv {
    border: 0px solid;
    height: 80px;
    padding-top: 20px;
}
.inputEnterpriseName {
    float: left;
    width: 200px;
}
.inputnumber {
    float: left;
    width: 200px;
}
.enterpriseName {
    float: left;
    line-height: 37px;
}
.number {
    float: left;
    line-height: 37px;
    padding-left: 30px;
}
.tabls {
    text-align: center;
}
.fr {
    float: right;
}
.selectData {
    color: rgb(64, 158, 255);
    font-size: 12px;
}
.tabs {
    table-layout: fixed;
    border-collapse: collapse;
    width: 90%;
    margin: auto;
}
.tabs th {
    width: 35%;
    line-height: 27px;
    text-align: center;
    border: 1px solid;
}
.tabs td {
    width: 65%;
    line-height: 27px;
    text-align: center;
    border: 1px solid;
}
.picture th {
    line-height: 100px;
}
</style>

