package cn.licoy.wdog.core.dto.aiship;import lombok.Data;import org.hibernate.validator.constraints.NotBlank;import javax.validation.constraints.NotNull;import javax.validation.constraints.Pattern;import javax.validation.constraints.Size;import java.util.List;import com.fasterxml.jackson.annotation.JsonFormat;import java.math.BigDecimal;import lombok.Data;import java.util.Date;/** * @author mc * @version Thu May 20 10:17:26 2021 */@Datapublic class DocUpdateDTO{   private String id;   private String title;   private String docType;   private String info;   @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   private Date createDate;}