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
                <el-button size="mini" @click="handleEdit(scope.$index, scope.row)">详情</el-button>
                <!-- <el-button size="mini" @click="handleRejected(scope.$index, scope.row)">驳回</el-button> -->
              </template>
            </el-table-column>
          </el-table>
        </div>
      </el-main>
    </el-container>
    <div style="text-align: left">
      <el-dialog
        title="派车单审核"
        :visible.sync="dialogVisible"
        width="50%"
        @close="empty"
        :close-on-click-modal="false"
      >
        <el-form
          :model="form"
          ref="addTruckingOrderForm"
          :disabled="true"
          style="margin: 0px;padding: 0px;"
        >
          <el-row>
            <el-col :span="6">
              <el-form-item label="用车部门:" prop="transportDepartment">
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
            </el-col>
            <el-col :span="6">
              <el-form-item label="使用人:" prop="user">
                <el-input
                  prefix-icon="el-icon-edit"
                  v-model="form.user"
                  size="mini"
                  style="width: 150px"
                  placeholder="请输入使用人"
                ></el-input>
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
                <!-- <el-input
                  prefix-icon="el-icon-edit"
                  v-model="form.licensePlateNumber"
                  size="mini"
                  style="width: 150px"
                  placeholder="请输入车辆品牌"
                ></el-input>-->
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
          <el-button size="mini" @click="handleRejected('addTruckingOrderForm')">驳 回</el-button>
          <el-button size="mini" type="primary" @click="handletHrough('addTruckingOrderForm')">通 过</el-button>
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
      departments: []
    };
  },
  mounted() {
    this.getList();
    this.getAvailableCar();
    this.getAllDeps();
  },
  methods: {
    handleEdit(index, row) {
      //详情
      this.dialogVisible = true;
      this.form = row;
      this.getAvailableCar();
      this.getAllDeps();
    },
    handletHrough() {
      //通过
      this.form.state = "已通过";
      this.putRequest("/truckingOrder/updateTruckingOrder", this.form).then(
        resp => {
          this.tableLoading = false;
          if (resp && resp.status == 200) {
            var data = resp.data;
            this.dialogVisible = false;
            this.getList();
          }
        }
      );
    },
    handleRejected(index, row) {
      //驳回
      this.form.state = "已驳回";
      this.putRequest("/truckingOrder/updateTruckingOrder", this.form).then(
        resp => {
          this.tableLoading = false;
          if (resp && resp.status == 200) {
            var data = resp.data;
            this.dialogVisible = false;
            this.getList();
          }
        }
      );
    },
    getList() {
      this.tableLoading = true;
      var _this = this;
      if (_this.keywords != "" && _this.keywords != null) {
        this.getRequest(
          "/truckingOrder/getTruckingOrderByCondition?user=" +
            this.keywords +
            "&state='待审核'"
        ).then(resp => {
          _this.tableLoading = false;
          if (resp && resp.status == 200) {
            _this.salaries = resp.data;
          }
        });
      } else {
        this.getRequest("/truckingOrder/getTruckingOrderByState").then(resp => {
          _this.tableLoading = false;
          if (resp && resp.status == 200) {
            _this.salaries = resp.data;
          }
        });
      }
    },
    getAvailableCar() {
      this.cars = [];
      this.getRequest("/car/getAvailableCar").then(resp => {
        if (resp && resp.status == 200) {
          this.cars = resp.data;
        }
      });
    },
    getAllDeps() {
      this.departments = [];
      this.getRequest("/truckingOrder/getAllDeps").then(resp => {
        if (resp && resp.status == 200) {
          this.departments = resp.data;
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
    }
  }
};
</script>
<style>
.el-dialog__body {
  padding-bottom: 10px;
}
</style>
