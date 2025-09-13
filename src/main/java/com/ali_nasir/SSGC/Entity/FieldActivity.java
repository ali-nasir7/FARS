package com.ali_nasir.SSGC.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "field_activities_data")  
public class FieldActivity {

    @Id
    @Column(name = "FA_ID")
    private String faId;

    @Column(name = "FO_ID")
    private String foId;

    @Column(name = "SP_ID")
    private String spId;

    @Column(name = "PREM_ID")
    private String premId;

    @Column(name = "ACCT_ID")
    private String acctId;

    @Column(name = "FA_TYPE_CD")
    private String faTypeCd;

    @Column(name = "FA_TYPE")
    private String faType;

    @Column(name = "FA_STATUS_FLG")
    private String faStatusFlg;

    @Column(name = "FA_STATUS")
    private String faStatus;

    @Column(name = "USER_ID")
    private String userId;

    @Column(name = "SCHED_DTTM")
    private LocalDateTime schedDttm;

    @Column(name = "CRE_DTTM")
    private String creDttm;

    @Column(name = "FA_TYPE_DESCR")
    private String faTypeDescr;

    @Column(name = "DISP_GRP_CD")
    private String dispGrpCd;

    @Column(name = "DISP_GRP_NAME")
    private String dispGrpName;

    @Column(name = "CANCEL_RSN_CD")
    private String cancelRsnCd;

    @Column(name = "FA_PRIOROITY_FLG")
    private String faPriorityFlg;

    @Column(name = "FA_CREATED_BY_FLG")
    private String faCreatedByFlg;

    @Column(name = "ELIG_DISPATCH_SW")
    private String eligDispatchSw;

   @Column(name = "INSTRUCTIONS", length = 1000)
    private String instructions;

    @Column(name = "DESCR254")
    private String descr254;

    @Column(name = "VERSION")
    private String version;

    @Column(name = "FO_WORKED_BY")
    private String foWorkedBy;

    @Column(name = "FO_WORKED_DTTM")
    private LocalDateTime foWorkedDttm;

    @Column(name = "NAME")
    private String name;

    @Column(name = "ADDRESS_1")
    private String address1;

    @Column(name = "ADDRESS_2")
    private String address2;

    @Column(name = "ADDRESS_3")
    private String address3;

    @Column(name = "ADDRESS_4")
    private String address4;

    @Column(name = "CITY")
    private String city;

    @Column(name = "AGING")
    private String aging;

    @Column(name = "CUST_CL_CD")
    private String custClCd;

    @Column(name = "UNIT")
    private String unit;

    @Column(name = "REGION")
    private String region;

    @Column(name = "ZONES")
    private String zones;

    @Column(name = "SUBZONE")
    private String subzone;

    @Column(name = "AREA_DESCR")
    private String areaDescr;

    @Column(name = "AREA")
    private String area;

    @Column(name = "ACT_DTTM")
    private String actDttm;

    @Column(name = "REASON")
    private String reason;

    @Column(name = "METER_NO")
    private String meterNo;

    @Column(name = "REMARKS")
    private String remarks;

    @Column(name = "MONTH")
    private String month;

    @Column(name = "SP_TYPE_CD")
    private String spTypeCd;

    @Column(name = "METER_TYPE")
    private String meterType;

    @Column(name = "BOOK_ROUTE")
    private String bookRoute;

    
}
