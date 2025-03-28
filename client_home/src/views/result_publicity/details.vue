<template>
	<div class="diy_details page_result_publicity" id="result_publicity_details">
		<div class='warp'>
			<div class='container'>
				<div class='row'>
					<div class='col'>
						<div class='card_result_publicity'>
							<div_result_publicity :obj="obj"></div_result_publicity>



						</div>
					</div>
				</div>
			</div>
		</div>

	</div>
</template>

<script>
	import div_result_publicity from "@/components/diy/div_result_publicity.vue";
	import mixin from "@/mixins/page.js";
	export default {
		mixins:[mixin],
		components:{
			div_result_publicity
		},
		data(){
			return{
				url_get_obj: "~/api/result_publicity/get_obj?",

				field: "result_publicity_id",

				obj: {
				},

				query: {
						"bidding_project": "",
							"bidding_classification": "",
							"tendering_unit": 0,
							"responsible_unit": "",
							"tender_cover": "",
							"bidding_merchant": 0,
							"supplier": "",
							"bid_amount": 0,
						"result_publicity_id":0,
				},
				// 点赞
				praise: 0,
				// 点赞状态
				state_praise: false,
			}
		},
		methods:{
			get_obj_after(json) {
				// 判断是否获取到数据
				if (json && json.result && json.result.obj) {
					console.log(json.result.obj)
					var obj = json.result.obj;
					this.obj = obj
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
