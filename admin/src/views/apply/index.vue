<template>
  <div class="app-container">
    <div class="filter-bar">
      <span>申请类型:</span>
      <el-select v-model="searchCondition.type">
        <el-option v-for="item in typeList" :key="item.value" :label="item.label" :value="item.value">
        </el-option>
      </el-select>
      <span>申请时间:</span>
      <el-date-picker v-model="searchCondition.start" type="date" placeholder="开始日期"
                      value-format="timestamp"></el-date-picker>
      <el-date-picker v-model="searchCondition.end" type="date" placeholder="结束日期"
                      value-format="timestamp"></el-date-picker>
      <span>申请状态:</span>
      <el-select v-model="searchCondition.status">
        <el-option v-for="item in statusList" :key="item.value" :label="item.label" :value="item.value">
        </el-option>
      </el-select>
      <el-input v-model="searchCondition.userName" placeholder="申请人姓名" class="filter-input"/>
      <el-button style="margin-left: 20px;" @click="searchApplyList">搜索</el-button>
    </div>
    <el-table :data="applyList" style="width: 100%" border fit max-height="700">
      <el-table-column prop="id" label="编号" align="center">
      </el-table-column>
      <el-table-column prop="type" label="申请类型" align="center">
        <template slot-scope="scope">
          <span v-if="scope.row.type === 1">种子基金申请</span>
          <span v-if="scope.row.type === 2">线下培训班报名</span>
          <span v-if="scope.row.type === 3">双创大赛报名</span>
        </template>
      </el-table-column>
      <el-table-column prop="userName" label="申请人" align="center">
      </el-table-column>
      <el-table-column prop="createTime" label="申请时间" align="center">
      </el-table-column>
      <el-table-column prop="status" label="状态" align="center">
        <template slot-scope="scope">
          <span v-if="scope.row.status === 0">未审核</span>
          <span v-if="scope.row.status === 1">通过</span>
          <span v-if="scope.row.status === 2">未通过</span>
          <span v-if="scope.row.status === 3">已过期</span>
        </template>
      </el-table-column>
      <el-table-column fixed="right" label="操作" align="center">
        <template slot-scope="scope">
          <el-button type="text" size="small" v-if="scope.row.status === 0" @click="pass(scope.row)">
            通过
          </el-button>
          <el-button type="text" size="small" v-if="scope.row.status === 0" @click="unpass(scope.row)">
            不通过
          </el-button>
          <el-button type="text" size="small">
            查看
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <div class="page-bar fr">
      <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
                     :current-page="searchCondition.current" :page-sizes="[5, 10, 20, 30]"
                     :page-size="searchCondition.pageSize" layout="total, sizes, prev, pager, next, jumper"
                     :total="total">
      </el-pagination>
    </div>
  </div>
</template>

<script>
  import {getApplyList,pass,unpass} from "../../api/apply";

  export default {
    data() {
      return {
        searchCondition: {
          userName: '',
          start: '',
          end: '',
          type: '',
          status: '',
          current: 1,
          pageSize: 10
        },
        typeList: [
          {
            label: "全部",
            value: ''
          },
          {
            label: "种子基金申请",
            value: 1
          },
          {
            label: "线下培训班报名",
            value: 2
          },
          {
            label: "双创大赛报名",
            value: 3
          },
        ],
        statusList: [
          {
            label: "全部",
            value: ''
          },
          {
            label: "未审核",
            value: 0
          },
          {
            label: "通过",
            value: 1
          },
          {
            label: "不通过",
            value: 2
          },
          {
            label: "过期",
            value: 3
          },
        ],
        total: 0,
        applyList: []
      }
    },
    created() {
      this.fetchData();
    },
    methods: {
      fetchData() {
        getApplyList(this.searchCondition).then(response => {
          this.total = response.data.total;
          this.applyList = response.data.records;
        });
      },
      handleSizeChange(val) {
        this.searchCondition.pageSize = val;
        this.searchCondition.current = 1;
        this.fetchData();
      },
      handleCurrentChange(val) {
        this.searchCondition.current = val;
        this.fetchData();
      },
      searchApplyList() {
        this.searchCondition.current = 1;
        this.fetchData();
      },
      pass(row) {
        this.$confirm('此操作将会通过审核, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          pass(row.id).then(response => {
            this.$message({
              message: "操作成功",
              type: "success",
              duration: 2 * 1000
            });
            row.status = 1;
          })
        })
      },
      unpass(row) {
        this.$confirm('此操作将会不通过审核, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          unpass(row.id).then(response => {
            this.$message({
              message: "操作成功",
              type: "success",
              duration: 2 * 1000
            });
            row.status = 2;
          })
        })
      }
    },
  }
</script>

<style scoped>
  .filter-bar {
    padding: 10px 0px 10px 0px;
  }

  .filter-bar span {
    margin-left: 20px;
  }

  .filter-bar .el-select {
    width: 130px;
  }

  .filter-input {
    margin-left: 20px;
    width: 150px;
  }

  .fr {
    float: right;
  }

  .fl {
    float: left;
  }
</style>

