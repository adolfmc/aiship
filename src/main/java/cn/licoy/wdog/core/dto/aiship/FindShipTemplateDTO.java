package cn.licoy.wdog.core.dto.aiship;import cn.licoy.wdog.core.dto.SplitPageDTO;import com.fasterxml.jackson.annotation.JsonFormat;import java.math.BigDecimal;import lombok.Data;import java.util.Date;/** * @author mc * @version Thu May 20 10:17:26 2021 */@Datapublic class FindShipTemplateDTO extends SplitPageDTO  {   private String customerId;   private String name;   private String company;   private String type;   private String age;   private String info;   private String size;   @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   private Date createDate;   private String id;   private String gangkou;   @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   private Date kongcDate;}