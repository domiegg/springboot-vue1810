<template>
	<div class="diy_details page_bidding_project" id="bidding_project_details">
		<div class='warp'>
			<div class='container'>
				<div class='row'>
					<div class='col'>
						<div class='card_bidding_project'>
							<div_bidding_project :obj="obj"></div_bidding_project>
							<div class='time_type' v-if='timeTypeShow'>
								<span>
									{{ obj['timer_title'] }}
								</span>
								<div id="start">
									<span>00</span>天
									<span>00</span>小时
									<span>00</span>分
									<span>00</span>秒
								</div>
							</div>



						</div>
					</div>
				</div>
			</div>
		</div>

	</div>
</template>

<script>
	import div_bidding_project from "@/components/diy/div_bidding_project.vue";
	import mixin from "@/mixins/page.js";
	export default {
		mixins:[mixin],
		components:{
			div_bidding_project
		},
		data(){
			return{
				url_get_obj: "~/api/bidding_project/get_obj?",

				field: "bidding_project_id",

				obj: {
				},

				query: {
						"bidding_project": "",
							"bidding_classification": "",
							"tendering_unit": 0,
							"responsible_unit": "",
							"work_address": "",
							"tender_cover": "",
							"introduction_to_bidding": "",
						"bidding_project_id":0,
					"timer_title":'', // 计时器标题
					"timing_start_time":'', // 计时开始时间
					"timing_end_time":'', // 计时结束时间
				},
				// 点赞
				praise: 0,
				// 点赞状态
				state_praise: false,
				timeTypeShow: true,
			}
		},
		methods:{
			/**
			 * 添加访问量
			 */
			add_hits(obj) {
				obj["hits"] = obj["hits"] + 1;
				var hits = obj["hits"];
				this.$post('~/api/bidding_project/set?bidding_project_id=' + obj["bidding_project_id"], {
					hits
				}, (res) => {
					if(res.result){
						console.log("添加访问量状态：" ,res.result);
						var body = {
							source_table: "bidding_project",
							source_field: "bidding_project_id",
							source_id: this.obj.bidding_project_id,
							user_id: this.$store.state.user.user_id,
						};
						this.$post("~/api/hits/add?", body, (res) => {
							console.log(res);
						});
					}
					else if(res.error){
						console.error(res.error);
					}
				});
			},
			get_obj_after(json) {
				// 判断是否获取到数据
				if (json && json.result && json.result.obj) {
					console.log(json.result.obj)
					var obj = json.result.obj;
					this.obj = obj
					// 增加点击数
					this.add_hits(obj);
					  if (JSON.stringify(this.obj["timing_end_time"]).indexOf("-")===-1) {
						this.obj["timing_end_time"] = this.$toTime(parseInt(this.obj["timing_end_time"]), "yyyy-MM-dd hh:mm:ss")
					  }
					  if (JSON.stringify(this.obj["timing_start_time"]).indexOf("-")===-1) {
						this.obj["timing_start_time"] = this.$toTime(parseInt(this.obj["timing_start_time"]), "yyyy-MM-dd hh:mm:ss")
					  }
					this.runTiming();
				}
			},

			/**
			 * 倒计时
			 */
			oDiv(el) {
				if(typeof(el) == 'string') {
					return(document.getElementById(el));
				}
				return(el);
			},

			fillZero(num, digit) {
				var str = '' + num;
				if(str.length < digit) {
					str = '0' + str;
				}
				return str;
			},
			runTiming() {
				var oDiv2 = this.oDiv('start');
				var aSpan = oDiv2.getElementsByTagName('span');
				var sTxtDay = aSpan[0]; //天
				var sTxtHour = aSpan[1] //小时
				var sTxtMin = aSpan[2] //分
				var sTxtSec = aSpan[3] //秒
				var timer = null;
				var _this = this;
				timer = setInterval(updateTime, 1000);
				updateTime();

				function updateTime() {
					var oEndDate = _this.obj.timing_end_time;
					var seart = _this.obj.timing_start_time;
          if(oEndDate.indexOf("-") != -1){
            oEndDate = new Date(oEndDate).getTime();
          }
          if(seart.indexOf("-") != -1){
            seart = new Date(seart).getTime();
          }
					var oNowDate = new Date();
					var iRemain = 0
					iRemain = parseInt((oNowDate.getTime() - seart) / 1000);
					if(iRemain <= 0) { //判断开始时间是否小于或等于今天
						clearInterval(timer);
						iRemain = 0;
						_this.timeTypeShow = false;
					}else{
					iRemain = parseInt((oEndDate - oNowDate.getTime()) / 1000);
						if(iRemain <= 0) { //判断结束时间是否小于或等于今天
							clearInterval(timer);
							iRemain = 0;
							_this.timeTypeShow = false;
						}else{
							_this.timeTypeShow = true;
							var iDay = parseInt(iRemain / 86400); //剩余天数

							iRemain %= 86400;
							var iHour = parseInt(iRemain / 3600); //剩余小时

							iRemain %= 3600;
							var iMin = parseInt(iRemain / 60); //剩余分钟

							iRemain %= 60;
							var iSec = iRemain; //剩余秒

							sTxtDay.innerHTML = _this.fillZero(iDay, 2);
							sTxtHour.innerHTML = _this.fillZero(iHour, 2);
							sTxtMin.innerHTML = _this.fillZero(iMin, 2);
							sTxtSec.innerHTML = _this.fillZero(iSec, 2);
						}
					}
				}
			},
		},

		mounted() {
		},

	}
</script>

<style>
.qecode {
  display: flex;
  align-content: center;
  width: 270px;
  background: white;
  position: absolute;
  left: 20%;
  top: 20px;
  z-index: 999;
}
.qecodeImg {
  display: flex;
  flex-direction: column;
  align-content: center;
  text-align: center;
  padding-top: 10px;
  margin: 0 auto;
}
.btn_share{
	display: inline-block;
	position: relative;
	top: 15px;
	background: rgb(240, 238, 238);
	border: 1px solid #ccc;
	height: 38px;
	margin-left: 10px;
	padding: 3px 10px;
	cursor: pointer;
	overflow: hidden;
	border-radius: 5px;
}
.btn_share_icon{
	width: 30px;
	height: 28px;
	float: left;
	margin-right: 10px;
}
.share_box{
	display: none;
	float: left;
}
.share_box img{
	width: 30px;
	height: 28px;
	margin-right: 10px;
}
.btn_share:hover .share_box{
	display: block;
}
</style>
