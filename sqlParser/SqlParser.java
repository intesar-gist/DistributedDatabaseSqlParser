/* SqlParser.java */
/* Generated By:JavaCC: Do not edit this line. SqlParser.java */
   package sqlParser;
   import java.util.ArrayList;
   import java.util.HashMap;
   import sqlParser.utilities.*;
   class SqlParser implements SqlParserConstants {
         ArrayList<QueryType> initParser() throws ParseException, TokenMgrError
   { return(init()) ; }

//Grammer Production Rules
  final public 
ArrayList<QueryType> init() throws ParseException {ArrayList<QueryType> queries = new ArrayList<QueryType>();
    QueryType queryType;
    label_1:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case R_CREATE:
      case R_DROP:
      case R_INSERT:{
        ;
        break;
        }
      default:
        jj_la1[0] = jj_gen;
        break label_1;
      }
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case R_CREATE:
      case R_DROP:{
        queryType = ProcessDDLQuery();
        break;
        }
      case R_INSERT:{
        queryType = ProcessDMLQuery();
        break;
        }
      default:
        jj_la1[1] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
queries.add(queryType);
    }
    jj_consume_token(0);
{if ("" != null) return queries;}
    throw new Error("Missing return statement in function");
  }

/********************************/
/********* DML queries **********/
/********************************/
  final public DMLQuery ProcessDMLQuery() throws ParseException {DMLQuery dmlQuery;
    dmlQuery = insert();
{if ("" != null) return dmlQuery;}
    throw new Error("Missing return statement in function");
  }

/* INSERT TABLE */
  final public DMLQuery insert() throws ParseException {Token T;
    Token queryType;
    DMLQuery dmlQuery;
    queryType = jj_consume_token(R_INSERT);
    T = jj_consume_token(S_IDENTIFIER);
    jj_consume_token(R_VALUES);
    jj_consume_token(O_OPENPAREN);
    InsertValueExpressions();
    jj_consume_token(O_CLOSEPAREN);
dmlQuery = new DMLQuery();
              dmlQuery.setTableName(T.image);
              dmlQuery.setQueryType(queryType.kind);
    jj_consume_token(O_TERMINATOR);
{if ("" != null) return dmlQuery;}
    throw new Error("Missing return statement in function");
  }

  final public void InsertValueExpressions() throws ParseException {
    label_2:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case QUOTED_STRING:{
        jj_consume_token(QUOTED_STRING);
        break;
        }
      case FLOAT:{
        jj_consume_token(FLOAT);
        break;
        }
      case INTEGER:{
        jj_consume_token(INTEGER);
        break;
        }
      case R_NULL:{
        jj_consume_token(R_NULL);
        break;
        }
      default:
        jj_la1[2] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case O_COMMA:{
        jj_consume_token(O_COMMA);
        break;
        }
      default:
        jj_la1[3] = jj_gen;
        ;
      }
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case FLOAT:
      case INTEGER:
      case R_NULL:
      case QUOTED_STRING:{
        ;
        break;
        }
      default:
        jj_la1[4] = jj_gen;
        break label_2;
      }
    }
  }

/********************************/
/********* DDL queries **********/
/********************************/
  final public 
DDLQuery ProcessDDLQuery() throws ParseException {DDLQuery ddlQuery;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case R_CREATE:{
      ddlQuery = create();
      break;
      }
    case R_DROP:{
      ddlQuery = drop();
      break;
      }
    default:
      jj_la1[5] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
{if ("" != null) return ddlQuery;}
    throw new Error("Missing return statement in function");
  }

/* DROP TABLE */
  final public DDLQuery drop() throws ParseException {Token T;
    Token queryType;
    DDLQuery ddlQuery;
    queryType = jj_consume_token(R_DROP);
    T = jj_consume_token(S_IDENTIFIER);
ddlQuery = new DDLQuery();
              ddlQuery.setTableName(T.image);
              ddlQuery.setQueryType(queryType.kind);
    jj_consume_token(O_TERMINATOR);
{if ("" != null) return ddlQuery;}
    throw new Error("Missing return statement in function");
  }

/* CREATE TABLE */
  final public DDLQuery create() throws ParseException {Token T;
    Token queryType;
    DDLQuery ddlQuery;
    HashMap<String,String> attributes;
    queryType = jj_consume_token(R_CREATE);
    T = jj_consume_token(S_IDENTIFIER);
ddlQuery = new DDLQuery ();
          ddlQuery.setTableName(T.image);
          ddlQuery.setQueryType(queryType.kind);
    jj_consume_token(O_OPENPAREN);
    attributes = ColumnsAndConstraints();
    jj_consume_token(O_CLOSEPAREN);
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case R_HORIZONTAL:{
      processHorizontal();
      break;
      }
    default:
      jj_la1[6] = jj_gen;
      ;
    }
    jj_consume_token(O_TERMINATOR);
ddlQuery.setAttributes(attributes);
        {if ("" != null) return ddlQuery;}
    throw new Error("Missing return statement in function");
  }

  final public void processHorizontal() throws ParseException {
    jj_consume_token(R_HORIZONTAL);
    jj_consume_token(O_OPENPAREN);
    jj_consume_token(S_IDENTIFIER);
    jj_consume_token(O_OPENPAREN);
    PartioningLimits();
    jj_consume_token(O_CLOSEPAREN);
    jj_consume_token(O_CLOSEPAREN);
  }

  final public void PartioningLimits() throws ParseException {
    label_3:
    while (true) {
      jj_consume_token(INTEGER);
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case INTEGER:{
        ;
        break;
        }
      default:
        jj_la1[7] = jj_gen;
        break label_3;
      }
    }
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case O_COMMA:{
      jj_consume_token(O_COMMA);
      jj_consume_token(INTEGER);
      break;
      }
    default:
      jj_la1[8] = jj_gen;
      ;
    }
  }

  final public HashMap ColumnsAndConstraints() throws ParseException {HashMap<String,String> var = new HashMap<String, String>();
    label_4:
    while (true) {
      var = Columns();
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case S_IDENTIFIER:{
        ;
        break;
        }
      default:
        jj_la1[9] = jj_gen;
        break label_4;
      }
    }
{if ("" != null) return var;}
    throw new Error("Missing return statement in function");
  }

  final public HashMap Columns() throws ParseException {Token TName;
   Token TType;
   HashMap<String,String> var = new HashMap<String, String>();
    TName = jj_consume_token(S_IDENTIFIER);
    //name of the column
        TType = ColType();
    jj_consume_token(O_COMMA);
    label_5:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case R_CONSTRAINT:{
        ;
        break;
        }
      default:
        jj_la1[10] = jj_gen;
        break label_5;
      }
      Constraints();
    }
var.put(TName.image,TType.image);
{if ("" != null) return var;}
    throw new Error("Missing return statement in function");
  }

  final public Token ColType() throws ParseException {Token TDType;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case R_INTEGER:{
      TDType = jj_consume_token(R_INTEGER);
      break;
      }
    case R_VARCHAR:{
      TDType = jj_consume_token(R_VARCHAR);
      break;
      }
    default:
      jj_la1[11] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case O_OPENPAREN:{
      jj_consume_token(O_OPENPAREN);
      jj_consume_token(INTEGER);
      jj_consume_token(O_CLOSEPAREN);
      break;
      }
    default:
      jj_la1[12] = jj_gen;
      ;
    }
{if ("" != null) return TDType;}
    throw new Error("Missing return statement in function");
  }

  final public HashMap Constraints() throws ParseException {Token TName;
   Token TType;
   HashMap<String,String> var = new HashMap<String, String>();
    TName = jj_consume_token(R_CONSTRAINT);
    TType = ConstraintType();
    jj_consume_token(O_COMMA);
var.put(TName.image,TType.image);
{if ("" != null) return var;}
    throw new Error("Missing return statement in function");
  }

  final public Token ConstraintType() throws ParseException {Token TDType;
    jj_consume_token(S_IDENTIFIER);
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case R_PRIMARY:{
      TDType = jj_consume_token(R_PRIMARY);
      break;
      }
    case R_UNIQUE:{
      TDType = jj_consume_token(R_UNIQUE);
      break;
      }
    default:
      jj_la1[13] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    jj_consume_token(O_OPENPAREN);
    jj_consume_token(S_IDENTIFIER);
    jj_consume_token(O_CLOSEPAREN);
{if ("" != null) return TDType;}
    throw new Error("Missing return statement in function");
  }

  /** Generated Token Manager. */
  public SqlParserTokenManager token_source;
  SimpleCharStream jj_input_stream;
  /** Current token. */
  public Token token;
  /** Next token. */
  public Token jj_nt;
  private int jj_ntk;
  private int jj_gen;
  final private int[] jj_la1 = new int[14];
  static private int[] jj_la1_0;
  static private int[] jj_la1_1;
  static {
      jj_la1_init_0();
      jj_la1_init_1();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x0,0x0,0xc00000,0x800,0xc00000,0x0,0x0,0x800000,0x800,0x0,0x0,0x0,0x400,0x0,};
   }
   private static void jj_la1_init_1() {
      jj_la1_1 = new int[] {0x700000,0x700000,0x40000800,0x0,0x40000800,0x300000,0x4000000,0x0,0x0,0x8000000,0x80000,0x3000000,0x0,0x808000,};
   }

  /** Constructor with InputStream. */
  public SqlParser(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public SqlParser(java.io.InputStream stream, String encoding) {
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new SqlParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 14; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 14; i++) jj_la1[i] = -1;
  }

  /** Constructor. */
  public SqlParser(java.io.Reader stream) {
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new SqlParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 14; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 14; i++) jj_la1[i] = -1;
  }

  /** Constructor with generated Token Manager. */
  public SqlParser(SqlParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 14; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(SqlParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 14; i++) jj_la1[i] = -1;
  }

  private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }


/** Get the next Token. */
  final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  private int jj_ntk_f() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  private int[] jj_expentry;
  private int jj_kind = -1;

  /** Generate ParseException. */
  public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[64];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 14; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
          if ((jj_la1_1[i] & (1<<j)) != 0) {
            la1tokens[32+j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 64; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  final public void enable_tracing() {
  }

  /** Disable tracing. */
  final public void disable_tracing() {
  }

   }
