package com.example.demo.pojo;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ContentRowHeight(20)//
@HeadRowHeight(20)
@ColumnWidth(20)

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Gain {
    private int gno;
    private String gname;
    private String gtype;
    private String tno;

    public Gain(String gname, String gtype) {
        this.gname = gname;
        this.gtype = gtype;
    }

    public Gain(String gname, String gtype, String tno) {
        this.gname = gname;
        this.gtype = gtype;
        this.tno = tno;
    }
}
