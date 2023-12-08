package models;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import constants.JpaConst;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * いいねデータのDTOモデル
 *
 */
@Table(name = JpaConst.TABLE_GOOD)
@NamedQueries({
    @NamedQuery(
            name = JpaConst.Q_GOOD_COUNT_ALL_MINE,
            query = JpaConst.Q_GOOD_COUNT_ALL_MINE_DEF),

    @NamedQuery(
            name = "findOne",
            query = "SELECT g FROM Good AS g WHERE g.employee = :emp AND g.report = :rep"),

    @NamedQuery(
            name = "isGood",
            query = "SELECT count(g) FROM Good AS g WHERE g.employee = :emp AND g.report = :rep")
})

@Getter //全てのクラスフィールドについてgetterを自動生成する(Lombok)
@Setter //全てのクラスフィールドについてsetterを自動生成する(Lombok)
@NoArgsConstructor //引数なしコンストラクタを自動生成する(Lombok)
@AllArgsConstructor //全てのクラスフィールドを引数にもつ引数ありコンストラクタを自動生成する(Lombok)
@Entity

public class Good {

//    String GOOD_COL_ID = "id"; //id
//    String GOOD_COL_REP = "report_id"; //いいねをつけた日報のid
//    String GOOD_COL_EMP = "person"; //いいねをつけた従業員のid
//    String GOOD_COL_CREATED_AT = "create_at"; //いいねの登録日時

    /**
     * id
     */
    @Id
    @Column(name = JpaConst.GOOD_COL_ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * いいねをつけた日報
     */
    @ManyToOne
    @JoinColumn(name = JpaConst.GOOD_COL_REP, nullable = false)
    private Report report;

    /**
     * いいねをつけた従業員
     */
    @ManyToOne
    @JoinColumn(name = JpaConst.GOOD_COL_EMP, nullable = false)
    private Employee employee;

    /**
     * 登録日時
     */
    @Column(name = JpaConst.GOOD_COL_CREATED_AT, nullable = false)
    private LocalDateTime createdAt;


}