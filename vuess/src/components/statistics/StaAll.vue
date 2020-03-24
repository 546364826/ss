<template>
  <div>
    <el-container>
      <el-header
        style="display: flex;justify-content: space-between;align-items: center;padding-left: 0px"
      >
        <div style="display: inline">
          <el-input
            placeholder="通过使用人搜索派车单,记得回车哦..."
            clearable
            style="width: 300px;margin: 0px;padding: 0px;"
            size="mini"
            @keyup.enter.native="getList"
            prefix-icon="el-icon-search"
            v-model="keywords"
          ></el-input>
          <el-button
            type="primary"
            size="mini"
            style="margin-left: 5px"
            icon="el-icon-search"
            @click="getList"
          >搜索</el-button>
        </div>
        <el-button
          @click="dialogVisible = true"
          icon="el-icon-plus"
          type="primary"
          size="mini"
        >添加派车单</el-button>
        <el-button size="mini" type="success" @click="getList" icon="el-icon-refresh"></el-button>
      </el-header>
      <el-main style="padding-left: 0px;padding-top: 0px">
        <div>
          <el-table
            :data="salaries"
            stripe
            v-loading="tableLoading"
            size="mini"
            style="width: 100%"
            border
          >
            <el-table-column width="100" prop="transportDepartment" label="用车部门" align="center">
              <template slot-scope="scope">{{ scope.row.transportDepartment }}</template>
            </el-table-column>
            <el-table-column width="80" prop="user" label="使用人" align="center">
              <template slot-scope="scope">{{ scope.row.user }}</template>
            </el-table-column>
            <el-table-column width="50" prop="busNumber" label="乘车人数" align="center">
              <template slot-scope="scope">{{ scope.row.busNumber }}</template>
            </el-table-column>
            <el-table-column width="85" prop="transportTime" label="用车时间" align="center">
              <template slot-scope="scope">{{ scope.row.transportTime | formatDate }}</template>
            </el-table-column>
            <el-table-column prop="destination" width="85" label="目的地" align="center">
              <template slot-scope="scope">{{ scope.row.destination }}</template>
            </el-table-column>
            <el-table-column width="120" prop="reason" label="因由" align="center">
              <template slot-scope="scope">{{ scope.row.reason }}</template>
            </el-table-column>
            <el-table-column prop="licensePlateNumber" width="90" label="使用车辆" align="center">
              <template slot-scope="scope">{{ scope.row.licensePlateNumber }}</template>
            </el-table-column>
            <el-table-column width="80" prop="driver" label="司机" align="center">
              <template slot-scope="scope">{{ scope.row.driver }}</template>
            </el-table-column>
            <el-table-column width="85" prop="writeTime" label="记录时间" align="center">
              <template slot-scope="scope">{{ scope.row.writeTime | formatDate }}</template>
            </el-table-column>
            <el-table-column width="85" prop="state" label="状态" align="center">
              <template slot-scope="scope">{{ scope.row.state}}</template>
            </el-table-column>
            <el-table-column prop="comment" width="150" label="备注" align="center">
              <template slot-scope="scope">{{ scope.row.comment }}</template>
            </el-table-column>

            <el-table-column label="操作" align="center">
              <template slot-scope="scope">
                <el-button
                  v-if="scope.row.state=='已保存'"
                  size="mini"
                  @click="handleEdit(scope.$index, scope.row)"
                >编辑</el-button>
                <el-button
                  v-if="scope.row.state=='已保存'"
                  size="mini"
                  @click="handleAudit(scope.$index, scope.row)"
                >审核</el-button>
                <el-button
                  v-if="scope.row.state=='待审核'"
                  size="mini"
                  @click="handleNotAudit(scope.$index, scope.row)"
                >取消审核</el-button>
                <el-button
                  size="mini"
                  type="danger"
                  v-if="scope.row.state=='已保存'"
                  @click="handleDelete(scope.$index, scope.row)"
                >删除</el-button>
              </template>
            </el-table-column>
          </el-table>
        </div>
      </el-main>
    </el-container>
    <div style="text-align: left">
      <el-dialog
        title="添加派车单"
        :visible.sync="dialogVisible"
        width="50%"
        @close="empty"
        :close-on-click-modal="false"
      >
        <el-form :model="form" ref="addTruckingOrderForm" style="margin: 0px;padding: 0px;">
          <el-row>
            <el-col :span="6">
              <el-form-item label="用车部门:" prop="transportDepartment">
                <!-- <el-input
                  prefix-icon="el-icon-edit"
                  v-model="form.transportDepartment"
                  size="mini"
                  style="width: 150px"
                  placeholder="请输入用车部门"
                ></el-input>-->
                <el-select
                  v-model="form.transportDepartment"
                  size="mini"
                  style="width: 150px"
                  placeholder="请选择用车部门"
                >
                  <el-option
                    v-for="(item,index) in departments"
                    :key="index"
                    :label="item.name"
                    :value="item.name"
                  ></el-option>
                </el-select>
              </el-form-item>
              <el-form-item label="目的地:" prop="destination">
                <el-input
                  prefix-icon="el-icon-edit"
                  v-model="form.destination"
                  size="mini"
                  style="width: 150px"
                  placeholder="请输入目的地"
                ></el-input>
              </el-form-item>
              <!-- <el-form-item label="记录时间:" prop="writeTime">
                <el-date-picker
                  v-model="form.writeTime"
                  size="mini"
                  style="width: 150px"
                  type="date"
                  value-format="yyyy-MM-dd"
                  format="yyyy-MM-dd"
                  placeholder="记录时间"
                ></el-date-picker>
              </el-form-item>-->
            </el-col>
            <el-col :span="6">
              <el-form-item label="使用人:" prop="user">
                <el-select
                  v-model="form.user"
                  size="mini"
                  style="width: 150px"
                  placeholder="请选择使用人"
                  filterable
                >
                  <el-option
                    v-for="(item,index) in users"
                    :key="index"
                    :label="item.name"
                    :value="item.name"
                  ></el-option>
                </el-select>
              </el-form-item>
              <el-form-item label="因由:" prop="reason">
                <el-input
                  prefix-icon="el-icon-edit"
                  v-model="form.reason"
                  size="mini"
                  style="width: 150px"
                  placeholder="请输入因由"
                ></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="乘车人数:" prop="busNumber">
                <el-input
                  prefix-icon="el-icon-edit"
                  v-model="form.busNumber"
                  size="mini"
                  style="width: 150px"
                  placeholder="请输入乘车人数"
                ></el-input>
              </el-form-item>

              <el-form-item label="可用车辆:" prop="licensePlateNumber">
                <el-select
                  v-model="form.licensePlateNumber"
                  size="mini"
                  style="width: 150px"
                  placeholder="请选择可用车辆"
                >
                  <el-option
                    v-for="(item,index) in cars"
                    :key="index"
                    :label="item.licensePlateNumber"
                    :value="item.licensePlateNumber"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="用车时间:" prop="transportTime">
                <el-date-picker
                  v-model="form.transportTime"
                  size="mini"
                  style="width: 150px"
                  type="date"
                  value-format="yyyy-MM-dd"
                  format="yyyy-MM-dd"
                  placeholder="用车时间"
                ></el-date-picker>
              </el-form-item>
              <el-form-item label="可用司机:" prop="driver">
                <el-input
                  prefix-icon="el-icon-edit"
                  v-model="form.driver"
                  size="mini"
                  style="width: 150px"
                  placeholder="请选择可用司机"
                ></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-form-item label="备注:" prop="comment">
            <el-input prefix-icon="el-icon-edit" v-model="form.comment" placeholder="请输入备注"></el-input>
          </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
          <el-button size="mini" @click="cancelEidt">取 消</el-button>
          <el-button
            size="mini"
            type="primary"
            @click="addTruckingOrder('addTruckingOrderForm')"
          >确 定</el-button>
        </span>
      </el-dialog>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      keywords: "",
      dialogVisible: false,
      tableLoading: false,
      index: 0,
      salaries: [],
      multipleSelection: [],
      form: {},
      selectForm: {},
      cars: [],
      departments: [],
      users: []
    };
  },
  mounted() {
    this.getList();
    this.getAvailableCar();
    this.getAllDeps();
    this.getAllUsers();
  },
  methods: {
    handleEdit(index, row) {
      this.dialogVisible = true;
      this.form = row;
      this.getAvailableCar();
      this.getAllDeps();
      this.getAllUsers();
    },
    handleAudit(index, row) {
      //审核
      row.state = "待审核";
      this.putRequest("/truckingOrder/updateTruckingOrder", row).then(resp => {
        _this.tableLoading = false;
        if (resp && resp.status == 200) {
          var data = resp.data;
          _this.dialogVisible = false;
          _this.getList();
        }
      });
    },
    handleNotAudit(index, row) {
      //取消审核
      row.state = "已保存";
      this.putRequest("/truckingOrder/updateTruckingOrder", row).then(resp => {
        _this.tableLoading = false;
        if (resp && resp.status == 200) {
          var data = resp.data;
          _this.dialogVisible = false;
          _this.getList();
        }
      });
    },
    handleDelete(index, row) {
      this.$confirm("永久删除该派车单, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          this.doDelete(row.id);
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消删除"
          });
        });
    },
    doDelete(id) {
      var _this = this;
      _this.tableLoading = true;
      this.deleteRequest("/truckingOrder/deleteTruckingOrderById/" + id).then(
        resp => {
          _this.tableLoading = false;
          if (resp && resp.status == 200) {
            var data = resp.data;
            _this.getList();
          }
        }
      );
    },
    addTruckingOrder() {
      var _this = this;
      this.form.state = "已保存";
      if (this.form.id) {
        //更新
        _this.tableLoading = true;
        this.putRequest("/truckingOrder/updateTruckingOrder", this.form).then(
          resp => {
            _this.tableLoading = false;
            if (resp && resp.status == 200) {
              var data = resp.data;
              _this.dialogVisible = false;
              _this.getList();
            }
          }
        );
      } else {
        //添加
        this.postRequest("/truckingOrder/addTruckingOrder", this.form)
          .then(resp => {
            if (resp && resp.status == 200) {
              var data = resp.data;
              _this.dialogVisible = false;
              _this.getList();
            }
          })
          .catch(() => {});
      }
    },
    getList() {
      this.tableLoading = true;
      var _this = this;
      if (_this.keywords != "" && _this.keywords != null) {
        this.getRequest(
          "/truckingOrder/getTruckingOrderByCondition?user=" + this.keywords
        ).then(resp => {
          _this.tableLoading = false;
          if (resp && resp.status == 200) {
            _this.salaries = resp.data;
          }
        });
      } else {
        this.getRequest("/truckingOrder/getTruckingOrder").then(resp => {
          _this.tableLoading = false;
          if (resp && resp.status == 200) {
            _this.salaries = resp.data;
          }
        });
      }
    },
    getAvailableCar() {
      //获取车辆信息
      this.cars = [];
      this.getRequest("/car/getAvailableCar").then(resp => {
        if (resp && resp.status == 200) {
          this.cars = resp.data;
        }
      });
    },
    getAllDeps() {
      //获取用车部门
      this.departments = [];
      this.getRequest("/truckingOrder/getAllDeps").then(resp => {
        if (resp && resp.status == 200) {
          this.departments = resp.data;
        }
      });
    },
    getAllUsers() {
      //获取使用人
      this.users = [];
      this.getRequest("/truckingOrder/getAllUsers").then(res => {
        if (res && res.status == 200) {
          this.users = res.data;
        }
      });
    },
    empty() {
      //清空form表单
      this.form = {
        transportDepartment: "",
        user: "",
        busNumber: "",
        transportTime: "",
        destination: "",
        reason: "",
        licensePlateNumber: "",
        driver: "",
        writeTime: "",
        comment: ""
      };
    },
    cancelEidt() {
      this.dialogVisible = false;
    }
  }
};
</script>
<style>
.el-dialog__body {
  padding-bottom: 10px;
}
</style>
