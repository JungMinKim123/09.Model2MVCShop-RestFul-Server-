<%@ page language="java" contentType="text/html; charset=EUC-KR"%>

<html>
<head>
<title>장바구니</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">

<script type="text/javascript">
<!--
function fncGetBasketList(){
	document.detailForm.submit();
}
-->
</script>
</head>

<body bgcolor="#ffffff" text="#000000">

<div style="width:98%; margin-left:10px;">

<form name="detailForm" action="/listProduct.do?menu=search" method="post">

<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
	<tr>
		<td width="15" height="37">
			<img src="/images/ct_ttl_img01.gif" width="15" height="37"/>
		</td>
		<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left:10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="93%" class="ct_ttl01">
					
							상품 목록조회
					
					</td>
				</tr>
			</table>
		</td>
		<td width="12" height="37">
			<img src="/images/ct_ttl_img03.gif" width="12" height="37"/>
		</td>
	</tr>
</table>


<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
		
		<td align="right">
			<select name="searchCondition" class="ct_input_g" style="width:80px">
				<option value="0">상품번호</option>
				<option value="1">상품명</option>
				<option value="2">상품가격</option>
			</select>
			<input type="text" name="searchKeyword"  class="ct_input_g" style="width:200px; height:19px" />
		</td>
	
		
		<td align="right" width="70">
			<table border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="17" height="23">
						<img src="/images/ct_btnbg01.gif" width="17" height="23">
					</td>
					<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top:3px;">
						<a href="javascript:fncGetProductList();">검색</a>
					</td>
					<td width="14" height="23">
						<img src="/images/ct_btnbg03.gif" width="14" height="23">
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>


<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
		<td colspan="11" >전체 100 건수, 현재 1 페이지</td>
	</tr>
	<tr>
		<td class="ct_list_b" width="100">No</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">상품명</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">가격</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">등록일</td>	
		<td class="ct_line02"></td>
		<td class="ct_list_b">현재상태</td>	
	</tr>
	<tr>
		<td colspan="11" bgcolor="808285" height="1"></td>
	</tr>
		
	<tr class="ct_list_pop">
		<td align="center">3</td>
		<td></td>
		
				<td align="left">변경2</td>
		
		<td></td>
		<td align="left">50003</td>
		<td></td>
		<td align="left">2012-12-14</td>
		<td></td>
		<td align="left">
		
			재고 없음
		
		</td>	
	</tr>
	<tr>
		<td colspan="11" bgcolor="D6D7D6" height="1"></td>
	</tr>	
	
	<tr class="ct_list_pop">
		<td align="center">2</td>
		<td></td>
		
				<td align="left">변경2</td>
		
		<td></td>
		<td align="left">50003</td>
		<td></td>
		<td align="left">2012-12-14</td>
		<td></td>
		<td align="left">
		
			재고 없음
		
		</td>	
	</tr>
	<tr>
		<td colspan="11" bgcolor="D6D7D6" height="1"></td>
	</tr>	
	
	<tr class="ct_list_pop">
		<td align="center">1</td>
		<td></td>
		
				<td align="left">변경2</td>
		
		<td></td>
		<td align="left">50003</td>
		<td></td>
		<td align="left">2012-12-14</td>
		<td></td>
		<td align="left">
		
			재고 없음
		
		</td>	
	</tr>
	<tr>
		<td colspan="11" bgcolor="D6D7D6" height="1"></td>
	</tr>	
	
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
		<td align="center">
		
			<a href="/listProduct.do?page=1&menu=search">1</a>
		
			<a href="/listProduct.do?page=2&menu=search">2</a>
		
			<a href="/listProduct.do?page=3&menu=search">3</a>
		
			<a href="/listProduct.do?page=4&menu=search">4</a>
		
			<a href="/listProduct.do?page=5&menu=search">5</a>
		
			<a href="/listProduct.do?page=6&menu=search">6</a>
		
			<a href="/listProduct.do?page=7&menu=search">7</a>
		
			<a href="/listProduct.do?page=8&menu=search">8</a>
		
			<a href="/listProduct.do?page=9&menu=search">9</a>
		
			<a href="/listProduct.do?page=10&menu=search">10</a>
		
			<a href="/listProduct.do?page=11&menu=search">11</a>
		
			<a href="/listProduct.do?page=12&menu=search">12</a>
		
			<a href="/listProduct.do?page=13&menu=search">13</a>
		
			<a href="/listProduct.do?page=14&menu=search">14</a>
		
			<a href="/listProduct.do?page=15&menu=search">15</a>
		
			<a href="/listProduct.do?page=16&menu=search">16</a>
		
			<a href="/listProduct.do?page=17&menu=search">17</a>
		
			<a href="/listProduct.do?page=18&menu=search">18</a>
		
			<a href="/listProduct.do?page=19&menu=search">19</a>
		
			<a href="/listProduct.do?page=20&menu=search">20</a>
		
			<a href="/listProduct.do?page=21&menu=search">21</a>
		
			<a href="/listProduct.do?page=22&menu=search">22</a>
		
			<a href="/listProduct.do?page=23&menu=search">23</a>
		
			<a href="/listProduct.do?page=24&menu=search">24</a>
		
			<a href="/listProduct.do?page=25&menu=search">25</a>
		
			<a href="/listProduct.do?page=26&menu=search">26</a>
		
			<a href="/listProduct.do?page=27&menu=search">27</a>
		
			<a href="/listProduct.do?page=28&menu=search">28</a>
		
			<a href="/listProduct.do?page=29&menu=search">29</a>
		
			<a href="/listProduct.do?page=30&menu=search">30</a>
		
			<a href="/listProduct.do?page=31&menu=search">31</a>
		
			<a href="/listProduct.do?page=32&menu=search">32</a>
		
			<a href="/listProduct.do?page=33&menu=search">33</a>
		
			<a href="/listProduct.do?page=34&menu=search">34</a>
		
    	</td>
	</tr>
</table>
<!--  페이지 Navigator 끝 -->

</form>

</div>
</body>
</html>
