# 의존성 흐름 UML

## 1. 패키지 의존성 다이어그램

```mermaid
flowchart TB
    subgraph Client["Client"]
        C[클라이언트 코드]
    end

    subgraph API["api (공개 인터페이스)"]
        Sign[Sign]
        ApprovalAction[ApprovalAction]
        ReviewAction[ReviewAction]
        subgraph Command["command"]
            Escalate[Escalate]
            Approvals[Approvals]
            Reviews[Reviews]
        end
    end

    subgraph SignDomain["sign"]
        SignFacade[SignFacade]
        SignService[SignService]
        SignRepo[SignRepository]
    end

    subgraph ApprovalDomain["approval"]
        ApprovalFacade[ApprovalActionFacade]
        ApprovalService[ApprovalService]
        ApprovalRepo[ApprovalRepository]
        ApprovalEntity[Approval]
    end

    subgraph ReviewDomain["review"]
        ReviewFacade[ReviewActionFacade]
        ReviewService[ReviewService]
        ReviewRepo[ReviewRepository]
        ReviewEntity[Review]
    end

    subgraph StepDomain["step"]
        ApprovalStepService[ApprovalStepService]
        ReviewStepService[ReviewStepService]
        StepRepo[StepRepository]
        ApprovalStep[ApprovalStep]
        ReviewStep[ReviewStep]
    end

    subgraph Common["common"]
        ProcessResult[ProcessResult]
    end

    C --> Sign
    Sign -.->|implements| SignFacade
    ApprovalAction -.->|implements| ApprovalFacade
    ReviewAction -.->|implements| ReviewFacade

    SignFacade --> SignService
    SignFacade --> ApprovalAction
    SignFacade --> ReviewAction

    ApprovalFacade --> ApprovalService
    ApprovalFacade --> SignService
    ApprovalFacade --> ProcessResult

    ReviewFacade --> ReviewService
    ReviewFacade --> SignService
    ReviewFacade --> ProcessResult

    SignService --> SignRepo
    SignService --> ApprovalService
    SignService --> ReviewService

    ApprovalService --> ApprovalRepo
    ApprovalService --> ApprovalStepService
    ApprovalService --> ApprovalEntity
    ApprovalEntity --> ApprovalStep

    ReviewService --> ReviewRepo
    ReviewService --> ReviewStepService
    ReviewService --> ReviewEntity
    ReviewEntity --> ReviewStep

    ApprovalStepService --> StepRepo
    ReviewStepService --> StepRepo
```

---

## 2. 클래스 다이어그램 (핵심 클래스)

```mermaid
classDiagram
    direction TB

    %% API Layer
    class Sign {
        <<interface>>
        +escalate(Escalate) SignResult
        +cancel(Cancel) SignResult
        +approvalAction() ApprovalAction
        +reviewAction() ReviewAction
    }

    class ApprovalAction {
        <<interface>>
        +approve(Submit) SignResult
        +reject(Submit) SignResult
    }

    class ReviewAction {
        <<interface>>
        +review(Submit) SignResult
        +reject(Submit) SignResult
    }

    %% Facade Layer
    class SignFacade {
        -SignService signService
        -ApprovalAction approvalAction
        -ReviewAction reviewAction
    }

    class ApprovalActionFacade {
        -SignService signService
        -ApprovalService approvalService
    }

    class ReviewActionFacade {
        -SignService signService
        -ReviewService reviewService
    }

    %% Service Layer
    class SignService {
        <<interface>>
        +escalate(Escalator, Approvals, Reviews) long
        +update(long, ProcessResult) void
    }

    class ApprovalService {
        <<interface>>
        +findOne(long) Approval
        +approve(Submit) ProcessResult
        +reject(Submit) ProcessResult
    }

    class ReviewService {
        <<interface>>
        +findOne(long) Review
        +review(Submit) ProcessResult
        +reject(Submit) ProcessResult
    }

    %% Entity Layer
    class Approval {
        -long signId
        -long id
        -ApprovalStatus status
        -List~ApprovalStep~ line
        +approve(long userId)
        +reject(long userId)
    }

    class Review {
        -long signId
        -long id
        -ReviewStatus status
        -Set~ReviewStep~ line
        +review(Submit submit)
        +reject(Submit submit)
    }

    %% Step Layer
    class ProcessStep {
        <<interface>>
        +id() long
        +deciderId() long
        +status() StepStatus
        +proceed(long requesterId)
        +reject(long requesterId)
    }

    class ApprovalStep {
        <<interface>>
        +waiting()
        +pass()
    }

    class ReviewStep {
        <<interface>>
    }

    %% Relationships
    Sign <|.. SignFacade : implements
    ApprovalAction <|.. ApprovalActionFacade : implements
    ReviewAction <|.. ReviewActionFacade : implements

    SignFacade --> SignService
    SignFacade --> ApprovalAction
    SignFacade --> ReviewAction

    ApprovalActionFacade --> SignService
    ApprovalActionFacade --> ApprovalService

    ReviewActionFacade --> SignService
    ReviewActionFacade --> ReviewService

    ApprovalService --> Approval
    ReviewService --> Review

    Approval o-- ApprovalStep : List
    Review o-- ReviewStep : Set

    ProcessStep <|-- ApprovalStep : extends
    ProcessStep <|-- ReviewStep : extends
```

---

## 3. 시퀀스 다이어그램 (승인 요청 흐름)

```mermaid
sequenceDiagram
    participant C as Client
    participant S as Sign
    participant SF as SignFacade
    participant SS as SignService
    participant AS as ApprovalService
    participant ASS as ApprovalStepService
    participant A as Approval
    participant Step as ApprovalStep

    C->>S: escalate(Escalate)
    S->>SF: escalate(Escalate)
    SF->>SS: escalate(escalator, approvals, reviews)
    SS->>SS: createSign()
    SS->>AS: create(approvals)
    AS->>ASS: assign(approvers)
    ASS->>Step: create steps
    SS-->>SF: signId
    SF-->>C: SignResult.escalated(signId)

    Note over C,Step: 승인 처리

    C->>S: approvalAction().approve(Submit)
    S->>SF: approvalAction()
    SF-->>C: ApprovalAction
    C->>AS: approve(Submit)
    AS->>A: findOne(signId)
    A->>A: approve(userId)
    A->>Step: proceed(userId)
    Step->>Step: status = APPROVED
    A->>Step: nextStep.waiting()
    AS-->>C: ProcessResult
```

---

## 4. 계층 구조 다이어그램

```mermaid
flowchart TB
    subgraph Layer1["API Layer (공개)"]
        direction LR
        A1[Sign]
        A2[ApprovalAction]
        A3[ReviewAction]
        A4[Command DTOs]
        A5[Result DTOs]
    end

    subgraph Layer2["Facade Layer (진입점)"]
        direction LR
        F1[SignFacade]
        F2[ApprovalActionFacade]
        F3[ReviewActionFacade]
    end

    subgraph Layer3["Service Layer (비즈니스 로직)"]
        direction LR
        S1[SignService]
        S2[ApprovalService]
        S3[ReviewService]
        S4[ApprovalStepService]
        S5[ReviewStepService]
    end

    subgraph Layer4["Entity Layer (도메인 모델)"]
        direction LR
        E1[Sign]
        E2[Approval]
        E3[Review]
        E4[ApprovalStep]
        E5[ReviewStep]
    end

    subgraph Layer5["Repository Layer (데이터 접근)"]
        direction LR
        R1[SignRepository]
        R2[ApprovalRepository]
        R3[ReviewRepository]
        R4[StepRepository]
    end

    subgraph Layer6["Infrastructure"]
        direction LR
        I1[MyBatis Mapper]
        I2[Oracle DB]
    end

    Layer1 --> Layer2
    Layer2 --> Layer3
    Layer3 --> Layer4
    Layer3 --> Layer5
    Layer5 --> Layer6
```

---

## 5. 의존성 방향 규칙

```mermaid
flowchart LR
    subgraph 의존_가능["의존 가능 방향"]
        direction LR
        API["API"] --> Service["Service"]
        Service --> Repository["Repository"]
        Service --> Entity["Entity"]
        Repository --> Entity
        Entity --> Common["Common/Enum"]
    end

    subgraph 의존_불가["의존 불가 방향"]
        direction RL
        Service2["Service"] -.->|X| API2["API"]
        Repository2["Repository"] -.->|X| Service2
        Entity2["Entity"] -.->|X| Repository2
    end

    style 의존_불가 fill:#ffcccc
```

---

## 6. ProcessStep 계층 구조

```mermaid
classDiagram
    direction TB

    class ProcessStep {
        <<interface>>
        +id() long
        +deciderId() long
        +status() StepStatus
        +setProcessId(long)
        +proceed(long requesterId)
        +reject(long requesterId)
        +isUpdated() boolean
    }

    class ApprovalStep {
        <<interface>>
        +waiting()
        +pass()
    }

    class ReviewStep {
        <<interface>>
    }

    class ApprovalStepImpl {
        -long stepId
        -long approvalId
        -long approverId
        -StepStatus status
        -boolean updated
    }

    class ReviewStepImpl {
        -long stepId
        -long reviewId
        -long reviewerId
        -StepStatus status
        -boolean updated
    }

    ProcessStep <|-- ApprovalStep
    ProcessStep <|-- ReviewStep
    ApprovalStep <|.. ApprovalStepImpl
    ReviewStep <|.. ReviewStepImpl

    note for ApprovalStep "순차 처리용\nwaiting() 메서드로\n다음 단계 활성화"
    note for ReviewStep "병렬 처리용\n추가 메서드 없음"
```

---

## 7. 설정 위임 구조

```mermaid
flowchart TB
    subgraph Client["클라이언트 프로젝트"]
        EC[CustomEnvironmentChecker]
        SR[CustomScriptRunner]
        RI[CustomRepositoryInitializer]
    end

    subgraph Library["결재 모듈"]
        ECI[EnvironmentChecker]
        SRI[ScriptRunner]
        RII[RepositoryInitializer]
        IAM[IAMInitializer]
    end

    EC -.->|implements| ECI
    SR -.->|implements| SRI
    RI -.->|implements| RII

    IAM --> ECI
    IAM --> SRI
    IAM --> RII

    subgraph Default["기본 구현체"]
        DEC[DevEnvironmentChecker]
        OSR[OracleScriptRunner]
        ORI[OracleRepositoryInitializer]
    end

    DEC -.->|implements| ECI
    OSR -.->|implements| SRI
    ORI -.->|implements| RII
```

---

## 다이어그램 보는 법

이 문서의 다이어그램은 **Mermaid** 문법으로 작성되었습니다.

**렌더링 방법:**
- GitHub, GitLab: 자동 렌더링
- VS Code: Mermaid 확장 설치
- IntelliJ: Mermaid 플러그인 설치
- 온라인: [Mermaid Live Editor](https://mermaid.live)

**PlantUML 변환이 필요한 경우:**
- [PlantUML 온라인 변환기](https://www.planttext.com) 사용
