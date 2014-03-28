typedef struct _hashelem {
	char *name;
	int index;
	struct _hashelem *next;
	struct _hashelem *nextelem;
} hashelem;
typedef struct {
	hashelem **table;
	int size;
	int base;
	int count;
	struct _hashelem *first;
	struct _hashelem *last;
} hashtable; /* _hashtable */
extern ""C"" {
static hashtable* create_hash_table(int size, int base);
	static void free_hash_table(hashtable* ht);
	static hashelem* findhash(const char* name, hashtable* ht);
	static hashelem* puthash(const char* name, int index, hashelem** list, hashtable* ht);
	static void drophash(const char* name, hashelem** list, hashtable* ht);
	static void free_hash_item(hashelem** hp);
	static hashtable* copy_hash_table(hashtable* ht, hashelem** list, int newsize);
	static int find_var(lprec* lp, char* name, MYBOOL verbose);
	static int find_row(lprec* lp, char* name, MYBOOL Unconstrained_rows_found);
}
extern ""C"" {
/** Convenience types. */
	typedef unsigned char __u_char;
	typedef unsigned short int __u_short;
	typedef unsigned int __u_int;
	typedef unsigned long int __u_long;
	/** Fixed-size types, underlying types depend on word size and compiler. */
	typedef signed char __int8_t;
	typedef unsigned char __uint8_t;
	typedef signed short int __int16_t;
	typedef unsigned short int __uint16_t;
	typedef signed int __int32_t;
	typedef unsigned int __uint32_t;
	typedef signed long long int __int64_t;
	typedef unsigned long long int __uint64_t;
	typedef long long int __quad_t;
	typedef unsigned long long int __u_quad_t;
	typedef __u_quad_t __dev_t; /* Type of device numbers.  */
	typedef unsigned int __uid_t; /* Type of user identifications.  */
	typedef unsigned int __gid_t; /* Type of group identifications.  */
	typedef unsigned long int __ino_t; /* Type of file serial numbers.  */
	typedef __u_quad_t __ino64_t; /* Type of file serial numbers (LFS).*/
	typedef unsigned int __mode_t; /* Type of file attribute bitmasks.  */
	typedef unsigned int __nlink_t; /* Type of file link counts.  */
	typedef long int __off_t; /* Type of file sizes and offsets.  */
	typedef __quad_t __off64_t; /* Type of file sizes and offsets (LFS).  */
	typedef int __pid_t; /* Type of process identifications.  */
	typedef struct {
		int __val[2]; /* Type of file system IDs.  */
	} __fsid_t; /* Type of file system IDs.  */
	typedef long int __clock_t; /* Type of CPU usage counts.  */
	typedef unsigned long int __rlim_t; /* Type for resource measurement.  */
	typedef __u_quad_t __rlim64_t; /* Type for resource measurement (LFS).  */
	typedef unsigned int __id_t; /* General type for IDs.  */
	typedef long int __time_t; /* Seconds since the Epoch.  */
	typedef unsigned int __useconds_t; /* Count of microseconds.  */
	typedef long int __suseconds_t; /* Signed count of microseconds.  */
	typedef int __daddr_t; /* The type of a disk address.  */
	typedef int __key_t; /* Type of an IPC key.  */
	/** Clock ID used in clock and timer functions. */
	typedef int __clockid_t;
	/** Timer ID returned by `timer_create'. */
	typedef void *__timer_t;
	/** Type to represent block size. */
	typedef long int __blksize_t;
	/** Type to count number of disk blocks. */
	typedef long int __blkcnt_t;
	typedef __quad_t __blkcnt64_t;
	/** Type to count file system blocks. */
	typedef unsigned long int __fsblkcnt_t;
	typedef __u_quad_t __fsblkcnt64_t;
	/** Type to count file system nodes. */
	typedef unsigned long int __fsfilcnt_t;
	typedef __u_quad_t __fsfilcnt64_t;
	/** Type of miscellaneous file system fields. */
	typedef int __fsword_t;
	typedef int __ssize_t; /* Type of a byte count, or error.  */
	/** Signed long type used in system calls. */
	typedef long int __syscall_slong_t;
	/** Unsigned long type used in system calls. */
	typedef unsigned long int __syscall_ulong_t;
	/**
	 * These few don't really vary by system, they always correspond<br>
	 * to one of the other defined types.
	 */
	typedef __off64_t __loff_t; /* Type of file sizes and offsets (LFS).  */
	typedef __quad_t *__qaddr_t;
	typedef char *__caddr_t;
	/** Duplicates info from stdint.h but this is used in unistd.h. */
	typedef int __intptr_t;
	/** Duplicate info from sys/socket.h. */
	typedef unsigned int __socklen_t;
	typedef __u_char u_char;
	typedef __u_short u_short;
	typedef __u_int u_int;
	typedef __u_long u_long;
	typedef __quad_t quad_t;
	typedef __u_quad_t u_quad_t;
	typedef __fsid_t fsid_t;
	typedef __loff_t loff_t;
	typedef __ino_t ino_t;
	typedef __dev_t dev_t;
	typedef __gid_t gid_t;
	typedef __mode_t mode_t;
	typedef __nlink_t nlink_t;
	typedef __uid_t uid_t;
	typedef __off_t off_t;
	typedef __pid_t pid_t;
	typedef __id_t id_t;
	typedef __ssize_t ssize_t;
	typedef __daddr_t daddr_t;
	typedef __caddr_t caddr_t;
	typedef __key_t key_t;
	/** Returned by `clock'. */
	typedef __clock_t clock_t;
	/** Returned by `time'. */
	typedef __time_t time_t;
	/** Clock ID used in clock and timer functions. */
	typedef __clockid_t clockid_t;
	/** Timer ID returned by `timer_create'. */
	typedef __timer_t timer_t;
	typedef long unsigned int size_t;
	/** Old compatibility names for C types. */
	typedef unsigned long int ulong;
	typedef unsigned short int ushort;
	typedef unsigned int uint;
	typedef char int8_t;
	typedef short int int16_t;
	typedef int int32_t;
	typedef long long int int64_t;
	/** But these were defined by ISO C without the first `_'. */
	typedef unsigned char u_int8_t;
	typedef unsigned short int u_int16_t;
	typedef unsigned int u_int32_t;
	typedef unsigned long long int u_int64_t;
	typedef int register_t;
	static __inline __uint64_t __bswap_64(__uint64_t __bsx) {
		return ((((__bsx) & 0xff00000000000000ull) >> 56) | (((__bsx) & 0x00ff000000000000ull) >> 40) | (((__bsx) & 0x0000ff0000000000ull) >> 24) | (((__bsx) & 0x000000ff00000000ull) >> 8) | (((__bsx) & 0x00000000ff000000ull) << 8) | (((__bsx) & 0x0000000000ff0000ull) << 24) | (((__bsx) & 0x000000000000ff00ull) << 40) | (((__bsx) & 0x00000000000000ffull) << 56));
	}
	typedef int __sig_atomic_t;
	typedef struct {
		unsigned long int __val[(1024 / (8 * sizeof(unsigned long int)))];
	} __sigset_t;
	typedef __sigset_t sigset_t;
	/**
	 * A time value that is accurate to the nearest<br>
	 * microsecond but also has a range of years.
	 */
	struct timeval {
		__time_t tv_sec; /* Seconds.  */
		__suseconds_t tv_usec; /* Microseconds.  */
	};
	typedef __suseconds_t suseconds_t;
	/** The fd_set member is required to be an array of longs. */
	typedef long int __fd_mask;
	/** fd_set for select and pselect. */
	typedef struct {
		__fd_mask __fds_bits[1024 / (8 * (int)sizeof(__fd_mask))];
	} fd_set;
	/** Sometimes the fd_set member is assumed to have this type. */
	typedef __fd_mask fd_mask;
	extern ""C"" {
/**
		 * Check the first NFDS descriptors each in READFDS (if not NULL) for read<br>
		 * readiness, in WRITEFDS (if not NULL) for write readiness, and in EXCEPTFDS<br>
		 * (if not NULL) for exceptional conditions.  If TIMEOUT is not NULL, time out<br>
		 * after waiting the interval specified therein.  Returns the number of ready<br>
		 * descriptors, or -1 for errors.<br>
		 * This function is a cancellation point and therefore not marked with<br>
		 * __THROW.
		 */
		extern int select(int __nfds, fd_set* __readfds, fd_set* __writefds, fd_set* __exceptfds, struct timeval* __timeout);
		/**
		 * Same as above only that the TIMEOUT value is given with higher<br>
		 * resolution and a sigmask which is been set temporarily.  This version<br>
		 * should be used.<br>
		 * This function is a cancellation point and therefore not marked with<br>
		 * __THROW.
		 */
		extern int pselect(int __nfds, fd_set* __readfds, fd_set* __writefds, fd_set* __exceptfds, struct const timespec* __timeout, const __sigset_t* __sigmask);
	}
	extern ""C"" {
extern unsigned int gnu_dev_major(unsigned long long int __dev);
		extern unsigned int gnu_dev_minor(unsigned long long int __dev);
		extern unsigned long long int gnu_dev_makedev(unsigned int __major, unsigned int __minor);
	}
	typedef __blksize_t blksize_t;
	typedef __blkcnt_t blkcnt_t; /* Type to count number of disk blocks.  */
	typedef __fsblkcnt_t fsblkcnt_t; /* Type to count file system blocks.  */
	typedef __fsfilcnt_t fsfilcnt_t; /* Type to count file system inodes.  */
	/**
	 * Thread identifiers.  The structure of the attribute type is not<br>
	 * exposed on purpose.
	 */
	typedef unsigned long int pthread_t;
	union  pthread_attr_t {
		char __size[36];
		long int __align;
	};
	typedef union  pthread_attr_t pthread_attr_t;
	typedef struct __pthread_internal_slist {
		struct __pthread_internal_slist *__next;
	} __pthread_slist_t;
	/**
	 * Data structures for mutex handling.  The structure of the attribute<br>
	 * type is not exposed on purpose.
	 */
	typedef union {
		struct __pthread_mutex_s {
			int __lock;
			unsigned int __count;
			int __owner;
			/**
			 * KIND must stay at this position in the structure to maintain<br>
			 * binary compatibility.
			 */
			int __kind;
			unsigned int __nusers;
			union {
				int __spins;
				__pthread_slist_t __list;
			};
		} __data;
		char __size[24];
		long int __align;
	} pthread_mutex_t;
	typedef union {
		char __size[4];
		int __align;
	} pthread_mutexattr_t;
	/**
	 * Data structure for conditional variable handling.  The structure of<br>
	 * the attribute type is not exposed on purpose.
	 */
	typedef union {
		struct {
			int __lock;
			unsigned int __futex;
			unsigned long long int __total_seq;
			unsigned long long int __wakeup_seq;
			unsigned long long int __woken_seq;
			void *__mutex;
			unsigned int __nwaiters;
			unsigned int __broadcast_seq;
		} __data;
		char __size[48];
		long long int __align;
	} pthread_cond_t;
	typedef union {
		char __size[4];
		int __align;
	} pthread_condattr_t;
	/** Keys for thread-specific data */
	typedef unsigned int pthread_key_t;
	/** Once-only execution */
	typedef int pthread_once_t;
	/**
	 * Data structure for read-write lock variable handling.  The<br>
	 * structure of the attribute type is not exposed on purpose.
	 */
	typedef union {
		struct {
			int __lock;
			unsigned int __nr_readers;
			unsigned int __readers_wakeup;
			unsigned int __writer_wakeup;
			unsigned int __nr_readers_queued;
			unsigned int __nr_writers_queued;
			/**
			 * FLAGS must stay at this position in the structure to maintain<br>
			 * binary compatibility.
			 */
			unsigned char __flags;
			unsigned char __shared;
			unsigned char __pad1;
			unsigned char __pad2;
			int __writer;
		} __data;
		char __size[32];
		long int __align;
	} pthread_rwlock_t;
	typedef union {
		char __size[8];
		long int __align;
	} pthread_rwlockattr_t;
	/** POSIX spinlock data type. */
	typedef volatile int pthread_spinlock_t;
	/**
	 * POSIX barriers data type.  The structure of the type is<br>
	 * deliberately not exposed.
	 */
	typedef union {
		char __size[20];
		long int __align;
	} pthread_barrier_t;
	typedef union {
		char __size[4];
		int __align;
	} pthread_barrierattr_t;
}
extern ""C"" {
union  wait {
		int w_status;
		struct {
			unsigned int __w_termsig:7; /* Terminating signal.  */
			unsigned int __w_coredump:1; /* Set if dumped core.  */
			unsigned int __w_retcode:8; /* Return code if exited normally.  */
			unsigned int :16;
		} __wait_terminated;
		struct {
			unsigned int __w_stopval:8; /* W_STOPPED if stopped.  */
			unsigned int __w_stopsig:8; /* Stopping signal.  */
			unsigned int :16;
		} __wait_stopped;
	};
	/** Returned by `div'. */
	typedef struct {
		int quot; /* Quotient.  */
		int rem; /* Remainder.  */
	} div_t;
	typedef struct {
		long int quot; /* Quotient.  */
		long int rem; /* Remainder.  */
	} ldiv_t;
	/** Returned by `lldiv'. */
	typedef struct {
		long long int quot; /* Quotient.  */
		long long int rem; /* Remainder.  */
	} lldiv_t;
	extern size_t __ctype_get_mb_cur_max();
	/** Convert a string to a floating-point number. */
	extern double atof(const char* __nptr);
	/** Convert a string to an integer. */
	extern int atoi(const char* __nptr);
	/** Convert a string to a long integer. */
	extern long int atol(const char* __nptr);
	/** Convert a string to a long long integer. */
	extern long long int atoll(const char* __nptr);
	/** Convert a string to a floating-point number. */
	extern double strtod(const char* __nptr, char** __endptr);
	/** Likewise for `float' and `long double' sizes of floating-point numbers. */
	extern float strtof(const char* __nptr, char** __endptr);
	extern long double strtold(const char* __nptr, char** __endptr);
	/** Convert a string to a long integer. */
	extern long int strtol(const char* __nptr, char** __endptr, int __base);
	/** Convert a string to an unsigned long integer. */
	extern unsigned long int strtoul(const char* __nptr, char** __endptr, int __base);
	extern long long int strtoq(const char* __nptr, char** __endptr, int __base);
	extern unsigned long long int strtouq(const char* __nptr, char** __endptr, int __base);
	extern long long int strtoll(const char* __nptr, char** __endptr, int __base);
	extern unsigned long long int strtoull(const char* __nptr, char** __endptr, int __base);
	/**
	 * Convert N to base 64 using the digits "./0-9A-Za-z", least-significant<br>
	 * digit first.  Returns a pointer to static storage overwritten by the<br>
	 * next call.
	 */
	extern char* l64a(long int __n);
	/** Read a number from a string S in base 64 as above. */
	extern long int a64l(const char* __s);
	/** Return a random long integer between 0 and RAND_MAX inclusive. */
	extern long int random();
	/** Seed the random number generator with the given number. */
	extern void srandom(unsigned int __seed);
	/**
	 * Initialize the random number generator to use state buffer STATEBUF,<br>
	 * of length STATELEN, and seed it with SEED.  Optimal lengths are 8, 16,<br>
	 * 32, 64, 128 and 256, the bigger the better; values less than 8 will<br>
	 * cause an error and values greater than 256 will be rounded down.
	 */
	extern char* initstate(unsigned int __seed, char* __statebuf, size_t __statelen);
	/**
	 * Switch the random number generator to state buffer STATEBUF,<br>
	 * which should have been previously initialized by `initstate'.
	 */
	extern char* setstate(char* __statebuf);
	struct random_data {
		int32_t *fptr; /* Front pointer.  */
		int32_t *rptr; /* Rear pointer.  */
		int32_t *state; /* Array of state values.  */
		int rand_type; /* Type of random number generator.  */
		int rand_deg; /* Degree of random number generator.  */
		int rand_sep; /* Distance between front and rear.  */
		int32_t *end_ptr; /* Pointer behind state table.  */
	};
	extern int random_r(struct random_data* __buf, int32_t* __result);
	extern int srandom_r(unsigned int __seed, struct random_data* __buf);
	extern int initstate_r(unsigned int __seed, char* __statebuf, size_t __statelen, struct random_data* __buf);
	extern int setstate_r(char* __statebuf, struct random_data* __buf);
	/** Return a random integer between 0 and RAND_MAX inclusive. */
	extern int rand();
	/** Seed the random number generator with the given number. */
	extern void srand(unsigned int __seed);
	/** Reentrant interface according to POSIX.1. */
	extern int rand_r(unsigned int* __seed);
	/** Return non-negative, double-precision floating-point value in [0.0,1.0). */
	extern double drand48();
	extern double erand48(unsigned short int __xsubi);
	/** Return non-negative, long integer in [0,2^31). */
	extern long int lrand48();
	extern long int nrand48(unsigned short int __xsubi);
	/** Return signed, long integers in [-2^31,2^31). */
	extern long int mrand48();
	extern long int jrand48(unsigned short int __xsubi);
	/** Seed random number generator. */
	extern void srand48(long int __seedval);
	extern unsigned short int* seed48(unsigned short int __seed16v);
	extern void lcong48(unsigned short int __param);
	/**
	 * Data structure for communication with thread safe versions.  This<br>
	 * type is to be regarded as opaque.  It's only exported because users<br>
	 * have to allocate objects of this type.
	 */
	struct drand48_data {
		unsigned short int __x[3]; /* Current state.  */
		unsigned short int __old_x[3]; /* Old state.  */
		unsigned short int __c; /* Additive const. in congruential formula.  */
		unsigned short int __init; /* Flag for initializing.  */
		unsigned long long int __a; /* Factor in congruential formula.  */
	};
	/** Return non-negative, double-precision floating-point value in [0.0,1.0). */
	extern int drand48_r(struct drand48_data* __buffer, double* __result);
	extern int erand48_r(unsigned short int __xsubi, struct drand48_data* __buffer, double* __result);
	/** Return non-negative, long integer in [0,2^31). */
	extern int lrand48_r(struct drand48_data* __buffer, long int* __result);
	extern int nrand48_r(unsigned short int __xsubi, struct drand48_data* __buffer, long int* __result);
	/** Return signed, long integers in [-2^31,2^31). */
	extern int mrand48_r(struct drand48_data* __buffer, long int* __result);
	extern int jrand48_r(unsigned short int __xsubi, struct drand48_data* __buffer, long int* __result);
	/** Seed random number generator. */
	extern int srand48_r(long int __seedval, struct drand48_data* __buffer);
	extern int seed48_r(unsigned short int __seed16v, struct drand48_data* __buffer);
	extern int lcong48_r(unsigned short int __param, struct drand48_data* __buffer);
	/** Allocate SIZE bytes of memory. */
	extern void* malloc(size_t __size);
	/** Allocate NMEMB elements of SIZE bytes each, all initialized to 0. */
	extern void* calloc(size_t __nmemb, size_t __size);
	/**
	 * __attribute_malloc__ is not used, because if realloc returns<br>
	 * the same pointer that was passed to it, aliasing needs to be allowed<br>
	 * between objects pointed by the old and new pointers.
	 */
	extern void* realloc(void* __ptr, size_t __size);
	/** Free a block allocated by `malloc', `realloc' or `calloc'. */
	extern void free(void* __ptr);
	/** Free a block.  An alias for `free'.	(Sun Unices). */
	extern void cfree(void* __ptr);
	extern ""C"" {
/** Allocate a block that will be freed when the calling function exits. */
		extern void* alloca(size_t __size);
	}
	/** Allocate SIZE bytes on a page boundary.  The storage cannot be freed. */
	extern void* valloc(size_t __size);
	/** Allocate memory of SIZE bytes with an alignment of ALIGNMENT. */
	extern int posix_memalign(void** __memptr, size_t __alignment, size_t __size);
	/** Abort execution and generate a core-dump. */
	extern void abort();
	/** Register a function to be called when `exit' is called. */
	extern int atexit(void (*__func)());
	/**
	 * Register a function to be called with the status<br>
	 * given to `exit' and the given argument.
	 */
	extern int on_exit(void (*__func)(int __status, void* __arg), void* __arg);
	/**
	 * Call all functions registered with `atexit' and `on_exit',<br>
	 * in the reverse of the order in which they were registered,<br>
	 * perform stdio cleanup, and terminate program execution with STATUS.
	 */
	extern void exit(int __status);
	/**
	 * Terminate the program with STATUS without calling any of the<br>
	 * functions registered with `atexit' or `on_exit'.
	 */
	extern void _Exit(int __status);
	/** Return the value of envariable NAME, or NULL if it doesn't exist. */
	extern char* getenv(const char* __name);
	/**
	 * Put STRING, which is of the form "NAME=VALUE", in the environment.<br>
	 * If there is no `=', remove NAME from the environment.
	 */
	extern int putenv(char* __string);
	/**
	 * Set NAME to VALUE in the environment.<br>
	 * If REPLACE is nonzero, overwrite an existing value.
	 */
	extern int setenv(const char* __name, const char* __value, int __replace);
	/** Remove the variable NAME from the environment. */
	extern int unsetenv(const char* __name);
	/**
	 * The `clearenv' was planned to be added to POSIX.1 but probably<br>
	 * never made it.  Nevertheless the POSIX.9 standard (POSIX bindings<br>
	 * for Fortran 77) requires this function.
	 */
	extern int clearenv();
	/**
	 * Generate a unique temporary file name from TEMPLATE.<br>
	 * The last six characters of TEMPLATE must be "XXXXXX";<br>
	 * they are replaced with a string that makes the file name unique.<br>
	 * Always returns TEMPLATE, it's either a temporary file name or a null<br>
	 * string if it cannot get a unique file name.
	 */
	extern char* mktemp(char* __template);
	extern int mkstemp(char* __template);
	extern int mkstemps(char* __template, int __suffixlen);
	/**
	 * Create a unique temporary directory from TEMPLATE.<br>
	 * The last six characters of TEMPLATE must be "XXXXXX";<br>
	 * they are replaced with a string that makes the directory name unique.<br>
	 * Returns TEMPLATE, or a null pointer if it cannot get a unique name.<br>
	 * The directory is created mode 700.
	 */
	extern char* mkdtemp(char* __template);
	/**
	 * Execute the given line as a shell command.<br>
	 * This function is a cancellation point and therefore not marked with<br>
	 * __THROW.
	 */
	extern int system(const char* __command);
	/**
	 * Return the canonical absolute name of file NAME.  If RESOLVED is<br>
	 * null, the result is malloc'd; otherwise, if the canonical name is<br>
	 * PATH_MAX chars or more, returns null with `errno' set to<br>
	 * ENAMETOOLONG; if the name fits in fewer than PATH_MAX chars,<br>
	 * returns the name in RESOLVED.
	 */
	extern char* realpath(const char* __name, char* __resolved);
	typedef int (*__compar_fn_t)(const void*, const void*);
	/**
	 * Do a binary search for KEY in BASE, which consists of NMEMB elements<br>
	 * of SIZE bytes each, using COMPAR to perform the comparisons.
	 */
	extern void* bsearch(const void* __key, const void* __base, size_t __nmemb, size_t __size, __compar_fn_t __compar);
	/**
	 * Sort NMEMB elements of BASE, of SIZE bytes each,<br>
	 * using COMPAR to perform the comparisons.
	 */
	extern void qsort(void* __base, size_t __nmemb, size_t __size, __compar_fn_t __compar);
	/** Return the absolute value of X. */
	extern int abs(int __x);
	extern long int labs(long int __x);
	extern long long int llabs(long long int __x);
	/** GCC may have built-ins for these someday. */
	extern div_t div(int __numer, int __denom);
	extern ldiv_t ldiv(long int __numer, long int __denom);
	extern lldiv_t lldiv(long long int __numer, long long int __denom);
	/**
	 * Convert VALUE to a string with NDIGIT digits and return a pointer to<br>
	 * this.  Set *DECPT with the position of the decimal character and *SIGN<br>
	 * with the sign of the number.
	 */
	extern char* ecvt(double __value, int __ndigit, int* __decpt, int* __sign);
	/**
	 * Convert VALUE to a string rounded to NDIGIT decimal digits.  Set *DECPT<br>
	 * with the position of the decimal character and *SIGN with the sign of<br>
	 * the number.
	 */
	extern char* fcvt(double __value, int __ndigit, int* __decpt, int* __sign);
	/**
	 * If possible convert VALUE to a string with NDIGIT significant digits.<br>
	 * Otherwise use exponential representation.  The resulting string will<br>
	 * be written to BUF.
	 */
	extern char* gcvt(double __value, int __ndigit, char* __buf);
	/** Long double versions of above functions. */
	extern char* qecvt(long double __value, int __ndigit, int* __decpt, int* __sign);
	extern char* qfcvt(long double __value, int __ndigit, int* __decpt, int* __sign);
	extern char* qgcvt(long double __value, int __ndigit, char* __buf);
	/**
	 * Reentrant version of the functions above which provide their own<br>
	 * buffers.
	 */
	extern int ecvt_r(double __value, int __ndigit, int* __decpt, int* __sign, char* __buf, size_t __len);
	extern int fcvt_r(double __value, int __ndigit, int* __decpt, int* __sign, char* __buf, size_t __len);
	extern int qecvt_r(long double __value, int __ndigit, int* __decpt, int* __sign, char* __buf, size_t __len);
	extern int qfcvt_r(long double __value, int __ndigit, int* __decpt, int* __sign, char* __buf, size_t __len);
	/**
	 * Return the length of the multibyte character<br>
	 * in S, which is no longer than N.
	 */
	extern int mblen(const char* __s, size_t __n);
	/**
	 * Return the length of the given multibyte character,<br>
	 * putting its `wchar_t' representation in *PWC.
	 */
	extern int mbtowc(wchar_t* __pwc, const char* __s, size_t __n);
	/**
	 * Put the multibyte character represented<br>
	 * by WCHAR in S, returning its length.
	 */
	extern int wctomb(char* __s, wchar_t __wchar);
	/** Convert a multibyte string to a wide char string. */
	extern size_t mbstowcs(wchar_t* __pwcs, const char* __s, size_t __n);
	/** Convert a wide char string to multibyte string. */
	extern size_t wcstombs(char* __s, const wchar_t* __pwcs, size_t __n);
	/**
	 * Determine whether the string value of RESPONSE matches the affirmation<br>
	 * or negative response expression as specified by the LC_MESSAGES category<br>
	 * in the program's current locale.  Returns 1 if affirmative, 0 if<br>
	 * negative, and -1 if not matching.
	 */
	extern int rpmatch(const char* __response);
	/**
	 * Parse comma separated suboption from *OPTIONP and match against<br>
	 * strings in TOKENS.  If found return index and set *VALUEP to<br>
	 * optional value introduced by an equal sign.  If the suboption is<br>
	 * not part of TOKENS return in *VALUEP beginning of unknown<br>
	 * suboption.  On exit *OPTIONP is set to the beginning of the next<br>
	 * token or at the terminating NUL character.
	 */
	extern int getsubopt(char** __optionp, const char** __tokens, char** __valuep);
	/**
	 * Put the 1 minute, 5 minute and 15 minute load averages into the first<br>
	 * NELEM elements of LOADAVG.  Return the number written (never more than<br>
	 * three, but may be less than NELEM), or -1 if an error occurred.
	 */
	extern int getloadavg(double __loadavg, int __nelem);
}
extern ""C"" {
/** Copy N bytes of SRC to DEST. */
	extern void* memcpy(void* __dest, const void* __src, size_t __n);
	/**
	 * Copy N bytes of SRC to DEST, guaranteeing<br>
	 * correct behavior for overlapping strings.
	 */
	extern void* memmove(void* __dest, const void* __src, size_t __n);
	extern void* memccpy(void* __dest, const void* __src, int __c, size_t __n);
	/** Set N bytes of S to C. */
	extern void* memset(void* __s, int __c, size_t __n);
	/** Compare N bytes of S1 and S2. */
	extern int memcmp(const void* __s1, const void* __s2, size_t __n);
	extern void* memchr(const void* __s, int __c, size_t __n);
	/** Copy SRC to DEST. */
	extern char* strcpy(char* __dest, const char* __src);
	/** Copy no more than N characters of SRC to DEST. */
	extern char* strncpy(char* __dest, const char* __src, size_t __n);
	/** Append SRC onto DEST. */
	extern char* strcat(char* __dest, const char* __src);
	/** Append no more than N characters from SRC onto DEST. */
	extern char* strncat(char* __dest, const char* __src, size_t __n);
	/** Compare S1 and S2. */
	extern int strcmp(const char* __s1, const char* __s2);
	/** Compare N characters of S1 and S2. */
	extern int strncmp(const char* __s1, const char* __s2, size_t __n);
	/** Compare the collated forms of S1 and S2. */
	extern int strcoll(const char* __s1, const char* __s2);
	/** Put a transformation of SRC into no more than N bytes of DEST. */
	extern size_t strxfrm(char* __dest, const char* __src, size_t __n);
	/**
	 * Structure for reentrant locale using functions.  This is an<br>
	 * (almost) opaque type for the user level programs.  The file and<br>
	 * this data structure is not standardized.  Don't rely on it.  It can<br>
	 * go away without warning.
	 */
	typedef struct __locale_struct {
		/** Note: LC_ALL is not a valid index into this array. */
		struct __locale_data *__locales[13]; /* 13 = __LC_LAST. */
		/** To increase the speed of this solution we add some special members. */
		const unsigned short int *__ctype_b;
		const int *__ctype_tolower;
		const int *__ctype_toupper;
		/** Note: LC_ALL is not a valid index into this array. */
		const char *__names[13];
	} *__locale_t;
	/** POSIX 2008 makes locale_t official. */
	typedef __locale_t locale_t;
	/** Compare the collated forms of S1 and S2 using rules from L. */
	extern int strcoll_l(const char* __s1, const char* __s2, __locale_t __l);
	/** Put a transformation of SRC into no more than N bytes of DEST. */
	extern size_t strxfrm_l(char* __dest, const char* __src, size_t __n, __locale_t __l);
	/** Duplicate S, returning an identical malloc'd string. */
	extern char* strdup(const char* __s);
	extern char* strndup(const char* __string, size_t __n);
	extern char* strchr(const char* __s, int __c);
	extern char* strrchr(const char* __s, int __c);
	/**
	 * Return the length of the initial segment of S which<br>
	 * consists entirely of characters not in REJECT.
	 */
	extern size_t strcspn(const char* __s, const char* __reject);
	/**
	 * Return the length of the initial segment of S which<br>
	 * consists entirely of characters in ACCEPT.
	 */
	extern size_t strspn(const char* __s, const char* __accept);
	extern char* strpbrk(const char* __s, const char* __accept);
	extern char* strstr(const char* __haystack, const char* __needle);
	/** Divide S into tokens separated by characters in DELIM. */
	extern char* strtok(char* __s, const char* __delim);
	/**
	 * Divide S into tokens separated by characters in DELIM.  Information<br>
	 * passed between calls are stored in SAVE_PTR.
	 */
	extern char* __strtok_r(char* __s, const char* __delim, char** __save_ptr);
	extern char* strtok_r(char* __s, const char* __delim, char** __save_ptr);
	/** Return the length of S. */
	extern size_t strlen(const char* __s);
	/**
	 * Find the length of STRING, but scan at most MAXLEN characters.<br>
	 * If no '\0' terminator is found in that many characters, return MAXLEN.
	 */
	extern size_t strnlen(const char* __string, size_t __maxlen);
	/** Return a string describing the meaning of the `errno' code in ERRNUM. */
	extern char* strerror(int __errnum);
	extern int __xpg_strerror_r(int __errnum, char* __buf, size_t __buflen);
	/** Translate error number to string according to the locale L. */
	extern char* strerror_l(int __errnum, __locale_t __l);
	/**
	 * We define this function always since `bzero' is sometimes needed when<br>
	 * the namespace rules does not allow this.
	 */
	extern void __bzero(void* __s, size_t __n);
	/** Copy N bytes of SRC to DEST (like memmove, but args reversed). */
	extern void bcopy(const void* __src, void* __dest, size_t __n);
	/** Set N bytes of S to 0. */
	extern void bzero(void* __s, size_t __n);
	/** Compare N bytes of S1 and S2 (same as memcmp). */
	extern int bcmp(const void* __s1, const void* __s2, size_t __n);
	extern char* index(const char* __s, int __c);
	extern char* rindex(const char* __s, int __c);
	/**
	 * Return the position of the first bit set in I, or 0 if none are set.<br>
	 * The least-significant bit is position 1, the most-significant 32.
	 */
	extern int ffs(int __i);
	/** Compare S1 and S2, ignoring case. */
	extern int strcasecmp(const char* __s1, const char* __s2);
	/** Compare no more than N chars of S1 and S2, ignoring case. */
	extern int strncasecmp(const char* __s1, const char* __s2, size_t __n);
	/**
	 * Return the next DELIM-delimited token from *STRINGP,<br>
	 * terminating it with a '\0', and update *STRINGP to point past it.
	 */
	extern char* strsep(char** __stringp, const char* __delim);
	/** Return a string describing the meaning of the signal number in SIG. */
	extern char* strsignal(int __sig);
	/** Copy SRC to DEST, returning the address of the terminating '\0' in DEST. */
	extern char* __stpcpy(char* __dest, const char* __src);
	extern char* stpcpy(char* __dest, const char* __src);
	/**
	 * Copy no more than N characters of SRC to DEST, returning the address of<br>
	 * the last character written into DEST.
	 */
	extern char* __stpncpy(char* __dest, const char* __src, size_t __n);
	extern char* stpncpy(char* __dest, const char* __src, size_t __n);
}
extern ""C"" {
/**
	 * The ix87 FPUs evaluate all values in the 80 bit floating-point format<br>
	 * which is also available for the user as `long double'.  Therefore we<br>
	 * define:
	 */
	typedef long double float_t; /* `float' expressions are evaluated as
				   `long double'.  */
	typedef long double double_t; /* `double' expressions are evaluated as
				   `long double'.  */
	/** Arc cosine of X. */
	extern double acos(double __x);
	extern double __acos(double __x);
	/** Arc sine of X. */
	extern double asin(double __x);
	extern double __asin(double __x);
	/** Arc tangent of X. */
	extern double atan(double __x);
	extern double __atan(double __x);
	/** Arc tangent of Y/X. */
	extern double atan2(double __y, double __x);
	extern double __atan2(double __y, double __x);
	/** Cosine of X. */
	extern double cos(double __x);
	extern double __cos(double __x);
	/** Sine of X. */
	extern double sin(double __x);
	extern double __sin(double __x);
	/** Tangent of X. */
	extern double tan(double __x);
	extern double __tan(double __x);
	/** Hyperbolic cosine of X. */
	extern double cosh(double __x);
	extern double __cosh(double __x);
	/** Hyperbolic sine of X. */
	extern double sinh(double __x);
	extern double __sinh(double __x);
	/** Hyperbolic tangent of X. */
	extern double tanh(double __x);
	extern double __tanh(double __x);
	/** Hyperbolic arc cosine of X. */
	extern double acosh(double __x);
	extern double __acosh(double __x);
	/** Hyperbolic arc sine of X. */
	extern double asinh(double __x);
	extern double __asinh(double __x);
	/** Hyperbolic arc tangent of X. */
	extern double atanh(double __x);
	extern double __atanh(double __x);
	/** Exponential function of X. */
	extern double exp(double __x);
	extern double __exp(double __x);
	/** Break VALUE into a normalized fraction and an integral power of 2. */
	extern double frexp(double __x, int* __exponent);
	extern double __frexp(double __x, int* __exponent);
	/** X times (two to the EXP power). */
	extern double ldexp(double __x, int __exponent);
	extern double __ldexp(double __x, int __exponent);
	/** Natural logarithm of X. */
	extern double log(double __x);
	extern double __log(double __x);
	/** Base-ten logarithm of X. */
	extern double log10(double __x);
	extern double __log10(double __x);
	/** Break VALUE into integral and fractional parts. */
	extern double modf(double __x, double* __iptr);
	extern double __modf(double __x, double* __iptr);
	/** Return exp(X) - 1. */
	extern double expm1(double __x);
	extern double __expm1(double __x);
	/** Return log(1 + X). */
	extern double log1p(double __x);
	extern double __log1p(double __x);
	/** Return the base 2 signed integral exponent of X. */
	extern double logb(double __x);
	extern double __logb(double __x);
	/** Compute base-2 exponential of X. */
	extern double exp2(double __x);
	extern double __exp2(double __x);
	/** Compute base-2 logarithm of X. */
	extern double log2(double __x);
	extern double __log2(double __x);
	/** Return X to the Y power. */
	extern double pow(double __x, double __y);
	extern double __pow(double __x, double __y);
	/** Return the square root of X. */
	extern double sqrt(double __x);
	extern double __sqrt(double __x);
	/** Return `sqrt(X*X + Y*Y)'. */
	extern double hypot(double __x, double __y);
	extern double __hypot(double __x, double __y);
	/** Return the cube root of X. */
	extern double cbrt(double __x);
	extern double __cbrt(double __x);
	/** Smallest integral value not less than X. */
	extern double ceil(double __x);
	extern double __ceil(double __x);
	/** Absolute value of X. */
	extern double fabs(double __x);
	extern double __fabs(double __x);
	/** Largest integer not greater than X. */
	extern double floor(double __x);
	extern double __floor(double __x);
	/** Floating-point modulo remainder of X/Y. */
	extern double fmod(double __x, double __y);
	extern double __fmod(double __x, double __y);
	/**
	 * Return 0 if VALUE is finite or NaN, +1 if it<br>
	 * is +Infinity, -1 if it is -Infinity.
	 */
	extern int __isinf(double __value);
	/** Return nonzero if VALUE is finite and not NaN. */
	extern int __finite(double __value);
	/**
	 * Return 0 if VALUE is finite or NaN, +1 if it<br>
	 * is +Infinity, -1 if it is -Infinity.
	 */
	extern int isinf(double __value);
	/** Return nonzero if VALUE is finite and not NaN. */
	extern int finite(double __value);
	/** Return the remainder of X/Y. */
	extern double drem(double __x, double __y);
	extern double __drem(double __x, double __y);
	/** Return the fractional part of X after dividing out `ilogb (X)'. */
	extern double significand(double __x);
	extern double __significand(double __x);
	/** Return X with its signed changed to Y's. */
	extern double copysign(double __x, double __y);
	extern double __copysign(double __x, double __y);
	/** Return representation of NaN for double type. */
	extern double nan(const char* __tagb);
	extern double __nan(const char* __tagb);
	/** Return nonzero if VALUE is not a number. */
	extern int __isnan(double __value);
	/** Return nonzero if VALUE is not a number. */
	extern int isnan(double __value);
	/** Bessel functions. */
	extern double j0(double);
	extern double __j0(double);
	extern double j1(double);
	extern double __j1(double);
	extern double jn(int, double);
	extern double __jn(int, double);
	extern double y0(double);
	extern double __y0(double);
	extern double y1(double);
	extern double __y1(double);
	extern double yn(int, double);
	extern double __yn(int, double);
	/** Error and gamma functions. */
	extern double erf(double);
	extern double __erf(double);
	extern double erfc(double);
	extern double __erfc(double);
	extern double lgamma(double);
	extern double __lgamma(double);
	/** True gamma function. */
	extern double tgamma(double);
	extern double __tgamma(double);
	/** Obsolete alias for `lgamma'. */
	extern double gamma(double);
	extern double __gamma(double);
	/**
	 * Reentrant version of lgamma.  This function uses the global variable<br>
	 * `signgam'.  The reentrant version instead takes a pointer and stores<br>
	 * the value through it.
	 */
	extern double lgamma_r(double, int* __signgamp);
	extern double __lgamma_r(double, int* __signgamp);
	/**
	 * Return the integer nearest X in the direction of the<br>
	 * prevailing rounding mode.
	 */
	extern double rint(double __x);
	extern double __rint(double __x);
	/** Return X + epsilon if X < Y, X - epsilon if X > Y. */
	extern double nextafter(double __x, double __y);
	extern double __nextafter(double __x, double __y);
	extern double nexttoward(double __x, long double __y);
	extern double __nexttoward(double __x, long double __y);
	/** Return the remainder of integer divison X / Y with infinite precision. */
	extern double remainder(double __x, double __y);
	extern double __remainder(double __x, double __y);
	/** Return X times (2 to the Nth power). */
	extern double scalbn(double __x, int __n);
	extern double __scalbn(double __x, int __n);
	/** Return the binary exponent of X, which must be nonzero. */
	extern int ilogb(double __x);
	extern int __ilogb(double __x);
	/** Return X times (2 to the Nth power). */
	extern double scalbln(double __x, long int __n);
	extern double __scalbln(double __x, long int __n);
	/**
	 * Round X to integral value in floating-point format using current<br>
	 * rounding direction, but do not raise inexact exception.
	 */
	extern double nearbyint(double __x);
	extern double __nearbyint(double __x);
	/**
	 * Round X to nearest integral value, rounding halfway cases away from<br>
	 * zero.
	 */
	extern double round(double __x);
	extern double __round(double __x);
	/**
	 * Round X to the integral value in floating-point format nearest but<br>
	 * not larger in magnitude.
	 */
	extern double trunc(double __x);
	extern double __trunc(double __x);
	/**
	 * Compute remainder of X and Y and put in *QUO a value with sign of x/y<br>
	 * and magnitude congruent `mod 2^n' to the magnitude of the integral<br>
	 * quotient x/y, with n >= 3.
	 */
	extern double remquo(double __x, double __y, int* __quo);
	extern double __remquo(double __x, double __y, int* __quo);
	/**
	 * Round X to nearest integral value according to current rounding<br>
	 * direction.
	 */
	extern long int lrint(double __x);
	extern long int __lrint(double __x);
	extern long long int llrint(double __x);
	extern long long int __llrint(double __x);
	/**
	 * Round X to nearest integral value, rounding halfway cases away from<br>
	 * zero.
	 */
	extern long int lround(double __x);
	extern long int __lround(double __x);
	extern long long int llround(double __x);
	extern long long int __llround(double __x);
	/** Return positive difference between X and Y. */
	extern double fdim(double __x, double __y);
	extern double __fdim(double __x, double __y);
	/** Return maximum numeric value from X and Y. */
	extern double fmax(double __x, double __y);
	extern double __fmax(double __x, double __y);
	/** Return minimum numeric value from X and Y. */
	extern double fmin(double __x, double __y);
	extern double __fmin(double __x, double __y);
	/** Classify given number. */
	extern int __fpclassify(double __value);
	/** Test for negative number. */
	extern int __signbit(double __value);
	/** Multiply-add function computed as a ternary operation. */
	extern double fma(double __x, double __y, double __z);
	extern double __fma(double __x, double __y, double __z);
	/** Return X times (2 to the Nth power). */
	extern double scalb(double __x, double __n);
	extern double __scalb(double __x, double __n);
	/** This variable is used by `gamma' and `lgamma'. */
	extern int signgam;
	/** All floating-point numbers can be put in one of these categories. */
	enum {
		FP_NAN = 0,
		FP_INFINITE = 1,
		FP_ZERO = 2,
		FP_SUBNORMAL = 3,
		FP_NORMAL = 4
	};
	/** Support for various different standard error handling behaviors. */
	typedef enum {
		_IEEE_ = -1 /* According to IEEE 754/IEEE 854.  */,
		_SVID_ /* According to System V, release 4.  */,
		_XOPEN_ /* Nowadays also Unix98.  */,
		_POSIX_,
		_ISOC_ /* Actually this is ISO C99.  */
	} _LIB_VERSION_TYPE;
	/**
	 * This variable can be changed at run-time to any of the values above to<br>
	 * affect floating point error handling behavior (it may also be necessary<br>
	 * to change the hardware FPU exception settings).
	 */
	extern _LIB_VERSION_TYPE _LIB_VERSION;
	struct __exception {
		int type;
		char *name;
		double arg1;
		double arg2;
		double retval;
	};
	extern int matherr(struct __exception* __exc) throw();
}
extern ""C"" {
/** Define outside of namespace so the C++ is happy. */
	struct _IO_FILE;
	/** The opaque type of streams.  This is the definition used elsewhere. */
	typedef struct _IO_FILE FILE;
	/** The opaque type of streams.  This is the definition used elsewhere. */
	typedef struct _IO_FILE __FILE;
	typedef unsigned int wint_t;
	/** Conversion state information. */
	typedef struct {
		int __count;
		union {
			wint_t __wch;
			char __wchb[4];
		} __value;
	} __mbstate_t;
	typedef struct {
		__off_t __pos;
		__mbstate_t __state;
	} _G_fpos_t;
	typedef struct {
		__off64_t __pos;
		__mbstate_t __state;
	} _G_fpos64_t;
	typedef __builtin_va_list __gnuc_va_list;
	struct _IO_jump_t;
	struct _IO_FILE;
	typedef void _IO_lock_t;
	struct _IO_marker {
		struct _IO_marker *_next;
		struct _IO_FILE *_sbuf;
		/** if _pos < 0, it points to _buf->eBptr()+_pos. FIXME comment */
		int _pos;
	};
	/** This is the structure from the libstdc++ codecvt class. */
	enum __codecvt_result {
		__codecvt_ok,
		__codecvt_partial,
		__codecvt_error,
		__codecvt_noconv
	};
	struct _IO_FILE {
		int _flags; /* High-order word is _IO_MAGIC; rest is flags. */
		/** Note:  Tk uses the _IO_read_ptr and _IO_read_end fields directly. */
		char *_IO_read_ptr; /* Current read pointer */
		char *_IO_read_end; /* End of get area. */
		char *_IO_read_base; /* Start of putback+get area. */
		char *_IO_write_base; /* Start of put area. */
		char *_IO_write_ptr; /* Current put pointer. */
		char *_IO_write_end; /* End of put area. */
		char *_IO_buf_base; /* Start of reserve area. */
		char *_IO_buf_end; /* End of reserve area. */
		/** The following fields are used to support backing up and undo. */
		char *_IO_save_base; /* Pointer to start of non-current get area. */
		char *_IO_backup_base; /* Pointer to first valid character of backup area */
		char *_IO_save_end; /* Pointer to end of non-current get area. */
		struct _IO_marker *_markers;
		struct _IO_FILE *_chain;
		int _fileno;
		int _flags2;
		__off_t _old_offset; /* This used to be _offset but it's too small.  */
		/** 1+column number of pbase(); 0 is unknown. */
		unsigned short _cur_column;
		signed char _vtable_offset;
		char _shortbuf[1];
		_IO_lock_t *_lock;
		__off64_t _offset;
		void *__pad1;
		void *__pad2;
		void *__pad3;
		void *__pad4;
		size_t __pad5;
		int _mode;
		/** Make sure we don't get into trouble again. */
		char _unused2[15 * sizeof(int) - 4 * sizeof(void*) - sizeof(size_t)];
	};
	struct _IO_FILE_plus;
	struct extern _IO_FILE_plus _IO_2_1_stdin_;
	struct extern _IO_FILE_plus _IO_2_1_stdout_;
	struct extern _IO_FILE_plus _IO_2_1_stderr_;
	/**
	 * Read NBYTES bytes from COOKIE into a buffer pointed to by BUF.<br>
	 * Return number of bytes read.
	 */
	typedef __ssize_t __io_read_fn(void* __cookie, char* __buf, size_t __nbytes);
	/**
	 * Write N bytes pointed to by BUF to COOKIE.  Write all N bytes<br>
	 * unless there is an error.  Return number of bytes written.  If<br>
	 * there is an error, return 0 and do not write anything.  If the file<br>
	 * has been opened for append (__mode.__append set), then set the file<br>
	 * pointer to the end of the file and then do the write; if not, just<br>
	 * write at the current file pointer.
	 */
	typedef __ssize_t __io_write_fn(void* __cookie, const char* __buf, size_t __n);
	/**
	 * Move COOKIE's file position to *POS bytes from the<br>
	 * beginning of the file (if W is SEEK_SET),<br>
	 * the current position (if W is SEEK_CUR),<br>
	 * or the end of the file (if W is SEEK_END).<br>
	 * Set *POS to the new file position.<br>
	 * Returns zero if successful, nonzero if not.
	 */
	typedef int __io_seek_fn(void* __cookie, __off64_t* __pos, int __w);
	/** Close COOKIE. */
	typedef int __io_close_fn(void* __cookie);
	extern ""C"" {
extern int __underflow(_IO_FILE*);
		extern int __uflow(_IO_FILE*);
		extern int __overflow(_IO_FILE*, int);
		extern int _IO_getc(_IO_FILE* __fp);
		extern int _IO_putc(int __c, _IO_FILE* __fp);
		extern int _IO_feof(_IO_FILE* __fp);
		extern int _IO_ferror(_IO_FILE* __fp);
		extern int _IO_peekc_locked(_IO_FILE* __fp);
		extern void _IO_flockfile(_IO_FILE*);
		extern void _IO_funlockfile(_IO_FILE*);
		extern int _IO_ftrylockfile(_IO_FILE*);
		extern int _IO_vfscanf(_IO_FILE*, const char*, __gnuc_va_list, int*);
		extern int _IO_vfprintf(_IO_FILE*, const char*, __gnuc_va_list);
		extern __ssize_t _IO_padn(_IO_FILE*, int, __ssize_t);
		extern size_t _IO_sgetn(_IO_FILE*, void*, size_t);
		extern __off64_t _IO_seekoff(_IO_FILE*, __off64_t, int, int);
		extern __off64_t _IO_seekpos(_IO_FILE*, __off64_t, int);
		extern void _IO_free_backup_area(_IO_FILE*);
	}
	typedef __gnuc_va_list va_list;
	typedef _G_fpos_t fpos_t;
	/** Standard streams. */
	struct extern _IO_FILE *stdin; /* Standard input stream.  */
	struct extern _IO_FILE *stdout; /* Standard output stream.  */
	struct extern _IO_FILE *stderr; /* Standard error output stream.  */
	/** Remove file FILENAME. */
	extern int remove(const char* __filename);
	/** Rename file OLD to NEW. */
	extern int rename(const char* __old, const char* __new);
	/** Rename file OLD relative to OLDFD to NEW relative to NEWFD. */
	extern int renameat(int __oldfd, const char* __old, int __newfd, const char* __new);
	extern FILE* tmpfile();
	/** Generate a temporary filename. */
	extern char* tmpnam(char* __s);
	/**
	 * This is the reentrant variant of `tmpnam'.  The only difference is<br>
	 * that it does not allow S to be NULL.
	 */
	extern char* tmpnam_r(char* __s);
	/**
	 * Generate a unique temporary filename using up to five characters of PFX<br>
	 * if it is not NULL.  The directory to put this file in is searched for<br>
	 * as follows: First the environment variable "TMPDIR" is checked.<br>
	 * If it contains the name of a writable directory, that directory is used.<br>
	 * If not and if DIR is not NULL, that value is checked.  If that fails,<br>
	 * P_tmpdir is tried and finally "/tmp".  The storage for the filename<br>
	 * is allocated by `malloc'.
	 */
	extern char* tempnam(const char* __dir, const char* __pfx);
	/**
	 * Close STREAM.<br>
	 * This function is a possible cancellation point and therefore not<br>
	 * marked with __THROW.
	 */
	extern int fclose(FILE* __stream);
	/**
	 * Flush STREAM, or all streams if STREAM is NULL.<br>
	 * This function is a possible cancellation point and therefore not<br>
	 * marked with __THROW.
	 */
	extern int fflush(FILE* __stream);
	/**
	 * Faster versions when locking is not required.<br>
	 * This function is not part of POSIX and therefore no official<br>
	 * cancellation point.  But due to similarity with an POSIX interface<br>
	 * or due to the implementation it is a cancellation point and<br>
	 * therefore not marked with __THROW.
	 */
	extern int fflush_unlocked(FILE* __stream);
	/**
	 * Open a file and create a new stream for it.<br>
	 * This function is a possible cancellation point and therefore not<br>
	 * marked with __THROW.
	 */
	extern FILE* fopen(const char* __filename, const char* __modes);
	/**
	 * Open a file, replacing an existing stream with it.<br>
	 * This function is a possible cancellation point and therefore not<br>
	 * marked with __THROW.
	 */
	extern FILE* freopen(const char* __filename, const char* __modes, FILE* __stream);
	/** Create a new stream that refers to an existing system file descriptor. */
	extern FILE* fdopen(int __fd, const char* __modes);
	/** Create a new stream that refers to a memory buffer. */
	extern FILE* fmemopen(void* __s, size_t __len, const char* __modes);
	/**
	 * Open a stream that writes into a malloc'd buffer that is expanded as<br>
	 * necessary.  *BUFLOC and *SIZELOC are updated with the buffer's location<br>
	 * and the number of characters written on fflush or fclose.
	 */
	extern FILE* open_memstream(char** __bufloc, size_t* __sizeloc);
	/**
	 * If BUF is NULL, make STREAM unbuffered.<br>
	 * Else make it use buffer BUF, of size BUFSIZ.
	 */
	extern void setbuf(FILE* __stream, char* __buf);
	/**
	 * Make STREAM use buffering mode MODE.<br>
	 * If BUF is not NULL, use N bytes of it for buffering;<br>
	 * else allocate an internal buffer N bytes long.
	 */
	extern int setvbuf(FILE* __stream, char* __buf, int __modes, size_t __n);
	/**
	 * If BUF is NULL, make STREAM unbuffered.<br>
	 * Else make it use SIZE bytes of BUF for buffering.
	 */
	extern void setbuffer(FILE* __stream, char* __buf, size_t __size);
	/** Make STREAM line-buffered. */
	extern void setlinebuf(FILE* __stream);
	/**
	 * Write formatted output to STREAM.<br>
	 * This function is a possible cancellation point and therefore not<br>
	 * marked with __THROW.
	 */
	extern int fprintf(FILE* __stream, const char* __format, ...);
	/**
	 * Write formatted output to stdout.<br>
	 * This function is a possible cancellation point and therefore not<br>
	 * marked with __THROW.
	 */
	extern int printf(const char* __format, ...);
	/** Write formatted output to S. */
	extern int sprintf(char* __s, const char* __format, ...);
	/**
	 * Write formatted output to S from argument list ARG.<br>
	 * This function is a possible cancellation point and therefore not<br>
	 * marked with __THROW.
	 */
	extern int vfprintf(FILE* __s, const char* __format, __gnuc_va_list __arg);
	/**
	 * Write formatted output to stdout from argument list ARG.<br>
	 * This function is a possible cancellation point and therefore not<br>
	 * marked with __THROW.
	 */
	extern int vprintf(const char* __format, __gnuc_va_list __arg);
	/** Write formatted output to S from argument list ARG. */
	extern int vsprintf(char* __s, const char* __format, __gnuc_va_list __arg);
	/** Maximum chars of output to write in MAXLEN. */
	extern int snprintf(char* __s, size_t __maxlen, const char* __format, ...);
	extern int vsnprintf(char* __s, size_t __maxlen, const char* __format, __gnuc_va_list __arg);
	/** Write formatted output to a file descriptor. */
	extern int vdprintf(int __fd, const char* __fmt, __gnuc_va_list __arg);
	extern int dprintf(int __fd, const char* __fmt, ...);
	/**
	 * Read formatted input from STREAM.<br>
	 * This function is a possible cancellation point and therefore not<br>
	 * marked with __THROW.
	 */
	extern int fscanf(FILE* __stream, const char* __format, ...);
	/**
	 * Read formatted input from stdin.<br>
	 * This function is a possible cancellation point and therefore not<br>
	 * marked with __THROW.
	 */
	extern int scanf(const char* __format, ...);
	/** Read formatted input from S. */
	extern int sscanf(const char* __s, const char* __format, ...);
	extern int __isoc99_fscanf(FILE* __stream, const char* __format, ...);
	extern int __isoc99_scanf(const char* __format, ...);
	extern int __isoc99_sscanf(const char* __s, const char* __format, ...);
	/**
	 * Read formatted input from S into argument list ARG.<br>
	 * This function is a possible cancellation point and therefore not<br>
	 * marked with __THROW.
	 */
	extern int vfscanf(FILE* __s, const char* __format, __gnuc_va_list __arg);
	/**
	 * Read formatted input from stdin into argument list ARG.<br>
	 * This function is a possible cancellation point and therefore not<br>
	 * marked with __THROW.
	 */
	extern int vscanf(const char* __format, __gnuc_va_list __arg);
	/** Read formatted input from S into argument list ARG. */
	extern int vsscanf(const char* __s, const char* __format, __gnuc_va_list __arg);
	extern int __isoc99_vfscanf(FILE* __s, const char* __format, __gnuc_va_list __arg);
	extern int __isoc99_vscanf(const char* __format, __gnuc_va_list __arg);
	extern int __isoc99_vsscanf(const char* __s, const char* __format, __gnuc_va_list __arg);
	/**
	 * Read a character from STREAM.<br>
	 * These functions are possible cancellation points and therefore not<br>
	 * marked with __THROW.
	 */
	extern int fgetc(FILE* __stream);
	extern int getc(FILE* __stream);
	/**
	 * Read a character from stdin.<br>
	 * This function is a possible cancellation point and therefore not<br>
	 * marked with __THROW.
	 */
	extern int getchar();
	/**
	 * These are defined in POSIX.1:1996.<br>
	 * These functions are possible cancellation points and therefore not<br>
	 * marked with __THROW.
	 */
	extern int getc_unlocked(FILE* __stream);
	extern int getchar_unlocked();
	/**
	 * Faster version when locking is not necessary.<br>
	 * This function is not part of POSIX and therefore no official<br>
	 * cancellation point.  But due to similarity with an POSIX interface<br>
	 * or due to the implementation it is a cancellation point and<br>
	 * therefore not marked with __THROW.
	 */
	extern int fgetc_unlocked(FILE* __stream);
	/**
	 * Write a character to STREAM.<br>
	 * These functions are possible cancellation points and therefore not<br>
	 * marked with __THROW.<br>
	 * These functions is a possible cancellation point and therefore not<br>
	 * marked with __THROW.
	 */
	extern int fputc(int __c, FILE* __stream);
	extern int putc(int __c, FILE* __stream);
	/**
	 * Write a character to stdout.<br>
	 * This function is a possible cancellation point and therefore not<br>
	 * marked with __THROW.
	 */
	extern int putchar(int __c);
	/**
	 * Faster version when locking is not necessary.<br>
	 * This function is not part of POSIX and therefore no official<br>
	 * cancellation point.  But due to similarity with an POSIX interface<br>
	 * or due to the implementation it is a cancellation point and<br>
	 * therefore not marked with __THROW.
	 */
	extern int fputc_unlocked(int __c, FILE* __stream);
	/**
	 * These are defined in POSIX.1:1996.<br>
	 * These functions are possible cancellation points and therefore not<br>
	 * marked with __THROW.
	 */
	extern int putc_unlocked(int __c, FILE* __stream);
	extern int putchar_unlocked(int __c);
	/** Get a word (int) from STREAM. */
	extern int getw(FILE* __stream);
	/** Write a word (int) to STREAM. */
	extern int putw(int __w, FILE* __stream);
	/**
	 * Get a newline-terminated string of finite length from STREAM.<br>
	 * This function is a possible cancellation point and therefore not<br>
	 * marked with __THROW.
	 */
	extern char* fgets(char* __s, int __n, FILE* __stream);
	/**
	 * Get a newline-terminated string from stdin, removing the newline.<br>
	 * DO NOT USE THIS FUNCTION!!  There is no limit on how much it will read.<br>
	 * The function has been officially removed in ISO C11.  This opportunity<br>
	 * is used to also remove it from the GNU feature list.  It is now only<br>
	 * available when explicitly using an old ISO C, Unix, or POSIX standard.<br>
	 * GCC defines _GNU_SOURCE when building C++ code and the function is still<br>
	 * in C++11, so it is also available for C++.<br>
	 * This function is a possible cancellation point and therefore not<br>
	 * marked with __THROW.
	 */
	extern char* gets(char* __s);
	/**
	 * Read up to (and including) a DELIMITER from STREAM into *LINEPTR<br>
	 * (and null-terminate it). *LINEPTR is a pointer returned from malloc (or<br>
	 * NULL), pointing to *N characters of space.  It is realloc'd as<br>
	 * necessary.  Returns the number of characters read (not including the<br>
	 * null terminator), or -1 on error or EOF.<br>
	 * These functions are not part of POSIX and therefore no official<br>
	 * cancellation point.  But due to similarity with an POSIX interface<br>
	 * or due to the implementation they are cancellation points and<br>
	 * therefore not marked with __THROW.
	 */
	extern __ssize_t __getdelim(char** __lineptr, size_t* __n, int __delimiter, FILE* __stream);
	extern __ssize_t getdelim(char** __lineptr, size_t* __n, int __delimiter, FILE* __stream);
	/**
	 * Like `getdelim', but reads up to a newline.<br>
	 * This function is not part of POSIX and therefore no official<br>
	 * cancellation point.  But due to similarity with an POSIX interface<br>
	 * or due to the implementation it is a cancellation point and<br>
	 * therefore not marked with __THROW.
	 */
	extern __ssize_t getline(char** __lineptr, size_t* __n, FILE* __stream);
	/**
	 * Write a string to STREAM.<br>
	 * This function is a possible cancellation point and therefore not<br>
	 * marked with __THROW.
	 */
	extern int fputs(const char* __s, FILE* __stream);
	/**
	 * Write a string, followed by a newline, to stdout.<br>
	 * This function is a possible cancellation point and therefore not<br>
	 * marked with __THROW.
	 */
	extern int puts(const char* __s);
	/**
	 * Push a character back onto the input buffer of STREAM.<br>
	 * This function is a possible cancellation point and therefore not<br>
	 * marked with __THROW.
	 */
	extern int ungetc(int __c, FILE* __stream);
	/**
	 * Read chunks of generic data from STREAM.<br>
	 * This function is a possible cancellation point and therefore not<br>
	 * marked with __THROW.
	 */
	extern size_t fread(void* __ptr, size_t __size, size_t __n, FILE* __stream);
	/**
	 * Write chunks of generic data to STREAM.<br>
	 * This function is a possible cancellation point and therefore not<br>
	 * marked with __THROW.
	 */
	extern size_t fwrite(const void* __ptr, size_t __size, size_t __n, FILE* __s);
	/**
	 * Faster versions when locking is not necessary.<br>
	 * These functions are not part of POSIX and therefore no official<br>
	 * cancellation point.  But due to similarity with an POSIX interface<br>
	 * or due to the implementation they are cancellation points and<br>
	 * therefore not marked with __THROW.
	 */
	extern size_t fread_unlocked(void* __ptr, size_t __size, size_t __n, FILE* __stream);
	extern size_t fwrite_unlocked(const void* __ptr, size_t __size, size_t __n, FILE* __stream);
	/**
	 * Seek to a certain position on STREAM.<br>
	 * This function is a possible cancellation point and therefore not<br>
	 * marked with __THROW.
	 */
	extern int fseek(FILE* __stream, long int __off, int __whence);
	/**
	 * Return the current position of STREAM.<br>
	 * This function is a possible cancellation point and therefore not<br>
	 * marked with __THROW.
	 */
	extern long int ftell(FILE* __stream);
	/**
	 * Rewind to the beginning of STREAM.<br>
	 * This function is a possible cancellation point and therefore not<br>
	 * marked with __THROW.
	 */
	extern void rewind(FILE* __stream);
	/**
	 * Seek to a certain position on STREAM.<br>
	 * This function is a possible cancellation point and therefore not<br>
	 * marked with __THROW.
	 */
	extern int fseeko(FILE* __stream, __off_t __off, int __whence);
	/**
	 * Return the current position of STREAM.<br>
	 * This function is a possible cancellation point and therefore not<br>
	 * marked with __THROW.
	 */
	extern __off_t ftello(FILE* __stream);
	/**
	 * Get STREAM's position.<br>
	 * This function is a possible cancellation point and therefore not<br>
	 * marked with __THROW.
	 */
	extern int fgetpos(FILE* __stream, fpos_t* __pos);
	/**
	 * Set STREAM's position.<br>
	 * This function is a possible cancellation point and therefore not<br>
	 * marked with __THROW.
	 */
	extern int fsetpos(FILE* __stream, const fpos_t* __pos);
	/** Clear the error and EOF indicators for STREAM. */
	extern void clearerr(FILE* __stream);
	/** Return the EOF indicator for STREAM. */
	extern int feof(FILE* __stream);
	/** Return the error indicator for STREAM. */
	extern int ferror(FILE* __stream);
	/** Faster versions when locking is not required. */
	extern void clearerr_unlocked(FILE* __stream);
	extern int feof_unlocked(FILE* __stream);
	extern int ferror_unlocked(FILE* __stream);
	/**
	 * Print a message describing the meaning of the value of errno.<br>
	 * This function is a possible cancellation point and therefore not<br>
	 * marked with __THROW.
	 */
	extern void perror(const char* __s);
	extern int sys_nerr;
	extern const char *const sys_errlist[];
	/** Return the system file descriptor for STREAM. */
	extern int fileno(FILE* __stream);
	/** Faster version when locking is not required. */
	extern int fileno_unlocked(FILE* __stream);
	/**
	 * Create a new stream connected to a pipe running the given command.<br>
	 * This function is a possible cancellation point and therefore not<br>
	 * marked with __THROW.
	 */
	extern FILE* popen(const char* __command, const char* __modes);
	/**
	 * Close a stream opened by popen and return the status of its child.<br>
	 * This function is a possible cancellation point and therefore not<br>
	 * marked with __THROW.
	 */
	extern int pclose(FILE* __stream);
	/** Return the name of the controlling terminal. */
	extern char* ctermid(char* __s);
	/** Acquire ownership of STREAM. */
	extern void flockfile(FILE* __stream);
	/**
	 * Try to acquire ownership of STREAM but do not block if it is not<br>
	 * possible.
	 */
	extern int ftrylockfile(FILE* __stream);
	/** Relinquish the ownership granted for STREAM. */
	extern void funlockfile(FILE* __stream);
}
/** ------------------------------------------------------------------------- */
typedef struct _lprec lprec;
typedef struct _INVrec INVrec;
union  QSORTrec;
/** B4 factorization optimization data */
typedef struct _B4rec {
	int *B4_var; /* Position of basic columns in the B4 basis */
	int *var_B4; /* Variable in the B4 basis */
	int *B4_row; /* B4 position of the i'th row */
	int *row_B4; /* Original position of the i'th row */
	double *wcol;
	int *nzwcol;
} B4rec;
typedef struct _OBJmonrec {
	lprec *lp;
	int oldpivstrategy, oldpivrule, pivrule, ruleswitches, limitstall[2], limitruleswitches, idxstep[5], countstep, startstep, currentstep, Rcycle, Ccycle, Ncycle, Mcycle, Icount;
	double thisobj, prevobj, objstep[5], thisinfeas, previnfeas, epsvalue;
	char spxfunc[10];
	unsigned char pivdynamic;
	unsigned char isdual;
	unsigned char active;
} OBJmonrec;
typedef struct _edgerec {
	double *edgeVector;
} edgerec;
typedef struct _pricerec {
	double theta;
	double pivot;
	double epspivot;
	int varno;
	lprec *lp;
	unsigned char isdual;
} pricerec;
typedef struct _multirec {
	lprec *lp;
	int size; /* The maximum number of multiply priced rows/columns */
	int used; /* The current / active number of multiply priced rows/columns */
	int limit; /* The active/used count at which a full update is triggered */
	pricerec *items; /* Array of best multiply priced rows/columns */
	int *freeList; /* The indeces of available positions in "items" */
	QSORTrec *sortedList; /* List of pointers to "pricerec" items in sorted order */
	double *stepList; /* Working array (values in sortedList order) */
	double *valueList; /* Working array (values in sortedList order) */
	int *indexSet; /* The final exported index list of pivot variables */
	int active; /* Index of currently active multiply priced row/column */
	int retries;
	double step_base;
	double step_last;
	double obj_base;
	double obj_last;
	double epszero;
	double maxpivot;
	double maxbound;
	unsigned char sorted;
	unsigned char truncinf;
	unsigned char objcheck;
	unsigned char dirty;
} multirec;
/** Temporary data storage arrays */
typedef struct _workarraysrec {
	lprec *lp;
	int size;
	int count;
	char **vectorarray;
	int *vectorsize;
} workarraysrec;
typedef struct _LLrec {
	int size; /* The allocated list size */
	int count; /* The current entry count */
	int firstitem;
	int lastitem;
	int *map; /* The list of forward and backward-mapped entries */
} LLrec;
typedef struct _PVrec {
	int count; /* The allocated list item count */
	int *startpos; /* Starting index of the current value */
	double *value; /* The list of forward and backward-mapped entries */
	struct _PVrec *parent; /* The parent record in a pushed chain */
} PVrec;
extern ""C"" {
/** Put function headers here */
	unsigned char allocCHAR(lprec* lp, char** ptr, int size, unsigned char clear);
	unsigned char allocMYBOOL(lprec* lp, unsigned char** ptr, int size, unsigned char clear);
	unsigned char allocINT(lprec* lp, int** ptr, int size, unsigned char clear);
	unsigned char allocREAL(lprec* lp, double** ptr, int size, unsigned char clear);
	unsigned char allocLREAL(lprec* lp, double** ptr, int size, unsigned char clear);
	unsigned char allocFREE(lprec* lp, void** ptr);
	double* cloneREAL(lprec* lp, double* origlist, int size);
	unsigned char* cloneMYBOOL(lprec* lp, unsigned char* origlist, int size);
	int* cloneINT(lprec* lp, int* origlist, int size);
	int comp_bits(unsigned char* bitarray1, unsigned char* bitarray2, int items);
	workarraysrec* mempool_create(lprec* lp);
	char* mempool_obtainVector(workarraysrec* mempool, int count, int unitsize);
	unsigned char mempool_releaseVector(workarraysrec* mempool, char* memvector, unsigned char forcefree);
	unsigned char mempool_free(workarraysrec** mempool);
	void roundVector(double* myvector, int endpos, double roundzero);
	double normalizeVector(double* myvector, int endpos);
	void swapINT(int* item1, int* item2);
	void swapREAL(double* item1, double* item2);
	void swapPTR(void** item1, void** item2);
	double restoreINT(double valREAL, double epsilon);
	double roundToPrecision(double value, double precision);
	int searchFor(int target, int* attributes, int size, int offset, unsigned char absolute);
	unsigned char isINT(lprec* lp, double value);
	unsigned char isOrigFixed(lprec* lp, int varno);
	void chsign_bounds(double* lobound, double* upbound);
	double rand_uniform(lprec* lp, double range);
	/** Doubly linked list routines */
	int createLink(int size, LLrec** linkmap, unsigned char* usedpos);
	unsigned char freeLink(LLrec** linkmap);
	int sizeLink(LLrec* linkmap);
	unsigned char isActiveLink(LLrec* linkmap, int itemnr);
	int countActiveLink(LLrec* linkmap);
	int countInactiveLink(LLrec* linkmap);
	int firstActiveLink(LLrec* linkmap);
	int lastActiveLink(LLrec* linkmap);
	unsigned char appendLink(LLrec* linkmap, int newitem);
	unsigned char insertLink(LLrec* linkmap, int afteritem, int newitem);
	unsigned char setLink(LLrec* linkmap, int newitem);
	unsigned char fillLink(LLrec* linkmap);
	int nextActiveLink(LLrec* linkmap, int backitemnr);
	int prevActiveLink(LLrec* linkmap, int forwitemnr);
	int firstInactiveLink(LLrec* linkmap);
	int lastInactiveLink(LLrec* linkmap);
	int nextInactiveLink(LLrec* linkmap, int backitemnr);
	int prevInactiveLink(LLrec* linkmap, int forwitemnr);
	int removeLink(LLrec* linkmap, int itemnr);
	LLrec* cloneLink(LLrec* sourcemap, int newsize, unsigned char freesource);
	int compareLink(LLrec* linkmap1, LLrec* linkmap2);
	unsigned char verifyLink(LLrec* linkmap, int itemnr, unsigned char doappend);
	/** Packed vector routines */
	PVrec* createPackedVector(int size, double* values, int* workvector);
	void pushPackedVector(PVrec* PV, PVrec* parent);
	unsigned char unpackPackedVector(PVrec* PV, double** target);
	double getvaluePackedVector(PVrec* PV, int index);
	PVrec* popPackedVector(PVrec* PV);
	unsigned char freePackedVector(PVrec** PV);
}
extern ""C"" {
/**
	 * Open the shared object FILE and map it in; return a handle that can be<br>
	 * passed to `dlsym' to get symbol values from it.
	 */
	extern void* dlopen(const char* __file, int __mode);
	/**
	 * Unmap and close a shared object opened by `dlopen'.<br>
	 * The handle cannot be used again after calling `dlclose'.
	 */
	extern int dlclose(void* __handle);
	/**
	 * Find the run-time address in the shared object HANDLE refers to<br>
	 * of the symbol called NAME.
	 */
	extern void* dlsym(void* __handle, const char* __name);
	/**
	 * When any of the above functions fails, call this function<br>
	 * to return a string describing the error.  Each call resets<br>
	 * the error string so that a following call returns null.
	 */
	extern char* dlerror();
}
/** Sparse matrix element (ordered columnwise) */
typedef struct _MATitem {
	int rownr;
	int colnr;
	double value;
} MATitem;
typedef struct _MATrec {
	/** Owner reference */
	lprec *lp;
	/** Active dimensions */
	int rows;
	int columns;
	/** Allocated memory */
	int rows_alloc;
	int columns_alloc;
	int mat_alloc; /* The allocated size for matrix sized structures */
	int *col_mat_colnr;
	int *col_mat_rownr;
	double *col_mat_value;
	int *col_end; /* columns_alloc+1 : col_end[i] is the index of the
                                   first element after column i; column[i] is stored
                                   in elements col_end[i-1] to col_end[i]-1 */
	int *col_tag; /* user-definable tag associated with each column */
	int *row_mat; /* mat_alloc : From index 0, row_mat contains the
                                   row-ordered index of the elements of col_mat */
	int *row_end; /* rows_alloc+1 : row_end[i] is the index of the
                                   first element in row_mat after row i */
	int *row_tag; /* user-definable tag associated with each row */
	double *colmax; /* Array of maximum values of each column */
	double *rowmax; /* Array of maximum values of each row */
	double epsvalue; /* Zero element rejection threshold */
	double infnorm; /* The largest absolute value in the matrix */
	double dynrange;
	unsigned char row_end_valid; /* TRUE if row_end & row_mat are valid */
	unsigned char is_roworder; /* TRUE if the current (temporary) matrix order is row-wise */
} MATrec;
typedef struct _DeltaVrec {
	lprec *lp;
	int activelevel;
	MATrec *tracker;
} DeltaVrec;
/** Sparse matrix routines */
MATrec* mat_create(lprec* lp, int rows, int columns, double epsvalue);
unsigned char mat_memopt(MATrec* mat, int rowextra, int colextra, int nzextra);
void mat_free(MATrec** matrix);
unsigned char inc_matrow_space(MATrec* mat, int deltarows);
int mat_mapreplace(MATrec* mat, LLrec* rowmap, LLrec* colmap, MATrec* insmat);
int mat_matinsert(MATrec* mat, MATrec* insmat);
int mat_zerocompact(MATrec* mat);
int mat_rowcompact(MATrec* mat, unsigned char dozeros);
int mat_colcompact(MATrec* mat, int prev_rows, int prev_cols);
unsigned char inc_matcol_space(MATrec* mat, int deltacols);
unsigned char inc_mat_space(MATrec* mat, int mindelta);
int mat_shiftrows(MATrec* mat, int* bbase, int delta, LLrec* varmap);
int mat_shiftcols(MATrec* mat, int* bbase, int delta, LLrec* varmap);
MATrec* mat_extractmat(MATrec* mat, LLrec* rowmap, LLrec* colmap, unsigned char negated);
int mat_appendrow(MATrec* mat, int count, double* row, int* colno, double mult, unsigned char checkrowmode);
int mat_appendcol(MATrec* mat, int count, double* column, int* rowno, double mult, unsigned char checkrowmode);
unsigned char mat_get_data(lprec* lp, int matindex, unsigned char isrow, int** rownr, int** colnr, double** value);
unsigned char mat_set_rowmap(MATrec* mat, int row_mat_index, int rownr, int colnr, int col_mat_index);
unsigned char mat_indexrange(MATrec* mat, int index, unsigned char isrow, int* startpos, int* endpos);
unsigned char mat_validate(MATrec* mat);
unsigned char mat_equalRows(MATrec* mat, int baserow, int comprow);
int mat_findelm(MATrec* mat, int row, int column);
int mat_findins(MATrec* mat, int row, int column, int* insertpos, unsigned char validate);
void mat_multcol(MATrec* mat, int col_nr, double mult, unsigned char DoObj);
double mat_getitem(MATrec* mat, int row, int column);
unsigned char mat_setitem(MATrec* mat, int row, int column, double value);
unsigned char mat_additem(MATrec* mat, int row, int column, double delta);
unsigned char mat_setvalue(MATrec* mat, int Row, int Column, double Value, unsigned char doscale);
int mat_nonzeros(MATrec* mat);
int mat_collength(MATrec* mat, int colnr);
int mat_rowlength(MATrec* mat, int rownr);
void mat_multrow(MATrec* mat, int row_nr, double mult);
void mat_multadd(MATrec* mat, double* lhsvector, int varnr, double mult);
unsigned char mat_setrow(MATrec* mat, int rowno, int count, double* row, int* colno, unsigned char doscale, unsigned char checkrowmode);
unsigned char mat_setcol(MATrec* mat, int colno, int count, double* column, int* rowno, unsigned char doscale, unsigned char checkrowmode);
unsigned char mat_mergemat(MATrec* target, MATrec* source, unsigned char usecolmap);
int mat_checkcounts(MATrec* mat, int* rownum, int* colnum, unsigned char freeonexit);
int mat_expandcolumn(MATrec* mat, int colnr, double* column, int* nzlist, unsigned char signedA);
unsigned char mat_computemax(MATrec* mat);
unsigned char mat_transpose(MATrec* mat);
WINAPI invert(lprec* lp, unsigned char shiftbounds, unsigned final char);
/** Vector compression and expansion routines */
unsigned char vec_compress(double* densevector, int startpos, int endpos, double epsilon, double* nzvector, int* nzindex);
unsigned char vec_expand(double* nzvector, int* nzindex, double* densevector, int startpos, int endpos);
/** Sparse matrix products */
unsigned char get_colIndexA(lprec* lp, int varset, int* colindex, unsigned char append);
int prod_Ax(lprec* lp, int* coltarget, double* input, int* nzinput, double roundzero, double ofscalar, double* output, int* nzoutput, int roundmode);
int prod_xA(lprec* lp, int* coltarget, double* input, int* nzinput, double roundzero, double ofscalar, double* output, int* nzoutput, int roundmode);
unsigned char prod_xA2(lprec* lp, int* coltarget, double* prow, double proundzero, int* pnzprow, double* drow, double droundzero, int* dnzdrow, double ofscalar, int roundmode);
/** Equation solution */
unsigned char fimprove(lprec* lp, double* pcol, int* nzidx, double roundzero);
void ftran(lprec* lp, double* rhsvector, int* nzidx, double roundzero);
unsigned char bimprove(lprec* lp, double* rhsvector, int* nzidx, double roundzero);
void btran(lprec* lp, double* rhsvector, int* nzidx, double roundzero);
/** Combined equation solution and matrix product for simplex operations */
unsigned char fsolve(lprec* lp, int varin, double* pcol, int* nzidx, double roundzero, double ofscalar, unsigned char prepareupdate);
unsigned char bsolve(lprec* lp, int row_nr, double* rhsvector, int* nzidx, double roundzero, double ofscalar);
void bsolve_xA2(lprec* lp, int* coltarget, int row_nr1, double* vector1, double roundzero1, int* nzvector1, int row_nr2, double* vector2, double roundzero2, int* nzvector2, int roundmode);
/** Change-tracking routines (primarily for B&B and presolve) */
DeltaVrec* createUndoLadder(lprec* lp, int levelitems, int maxlevels);
int incrementUndoLadder(DeltaVrec* DV);
unsigned char modifyUndoLadder(DeltaVrec* DV, int itemno, double target, double newvalue);
int countsUndoLadder(DeltaVrec* DV);
int restoreUndoLadder(DeltaVrec* DV, double target);
int decrementUndoLadder(DeltaVrec* DV);
unsigned char freeUndoLadder(DeltaVrec** DV);
/** Specialized presolve undo functions */
unsigned char appendUndoPresolve(lprec* lp, unsigned char isprimal, double beta, int colnrDep);
unsigned char addUndoPresolve(lprec* lp, unsigned char isprimal, int colnrElim, double alpha, double beta, int colnrDep);
/** Basis storage (mainly for B&B) */
typedef struct _basisrec {
	int level;
	int *var_basic;
	unsigned char *is_basic;
	unsigned char *is_lower;
	int pivots;
	struct _basisrec *previous;
} basisrec;
/** Presolve undo data storage */
typedef struct _presolveundorec {
	lprec *lp;
	int orig_rows;
	int orig_columns;
	int orig_sum;
	int *var_to_orig; /* sum_alloc+1 : Mapping of variables from solution to
                                   best_solution to account for removed variables and
                                   rows during presolve; a non-positive value indicates
                                   that the constraint or variable was removed */
	int *orig_to_var; /* sum_alloc+1 : Mapping from original variable index to
                                   current / working index number */
	double *fixed_rhs; /* rows_alloc+1 : Storage of values of presolved fixed colums */
	double *fixed_obj; /* columns_alloc+1: Storage of values of presolved fixed rows */
	DeltaVrec *deletedA; /* A matrix of eliminated data from matA */
	DeltaVrec *primalundo; /* Affine translation vectors for eliminated primal variables */
	DeltaVrec *dualundo; /* Affine translation vectors for eliminated dual variables */
	unsigned char OFcolsdeleted;
} presolveundorec;
/** Pseudo-cost arrays used during B&B */
typedef struct _BBPSrec {
	lprec *lp;
	int pseodotype;
	int updatelimit;
	int updatesfinished;
	double restartlimit;
	MATitem *UPcost;
	MATitem *LOcost;
	struct _BBPSrec *secondary;
} BBPSrec;
/** Bounds storage for B&B routines */
typedef struct _BBrec {
	struct _BBrec *parent;
	struct _BBrec *child;
	lprec *lp;
	int varno;
	int vartype;
	int lastvarcus; /* Count of non-int variables of the previous branch */
	int lastrcf;
	int nodesleft;
	int nodessolved;
	int nodestatus;
	double noderesult;
	double lastsolution; /* Optimal solution of the previous branch */
	double sc_bound;
	double *upbo, *lowbo;
	double UPbound, LObound;
	int UBtrack, LBtrack; /* Signals that incoming bounds were changed */
	unsigned char contentmode; /* Flag indicating if we "own" the bound vectors */
	unsigned char sc_canset;
	unsigned char isSOS;
	unsigned char isGUB;
	int *varmanaged; /* Extended list of variables managed by this B&B level */
	unsigned char isfloor; /* State variable indicating the active B&B bound */
	unsigned char UBzerobased; /* State variable indicating if bounds have been rebased */
} BBrec;
extern ""C"" {
BBrec* create_BB(lprec* lp, BBrec* parentBB, unsigned char dofullcopy);
	BBrec* push_BB(lprec* lp, BBrec* parentBB, int varno, int vartype, int varcus);
	unsigned char initbranches_BB(BBrec* BB);
	unsigned char fillbranches_BB(BBrec* BB);
	unsigned char nextbranch_BB(BBrec* BB);
	unsigned char strongbranch_BB(lprec* lp, BBrec* BB, int varno, int vartype, int varcus);
	unsigned char initcuts_BB(lprec* lp);
	int updatecuts_BB(lprec* lp);
	unsigned char freecuts_BB(lprec* lp);
	BBrec* findself_BB(BBrec* BB);
	int solve_LP(lprec* lp, BBrec* BB);
	int rcfbound_BB(BBrec* BB, int varno, unsigned char isINT, double* newbound, unsigned char* isfeasible);
	unsigned char findnode_BB(BBrec* BB, int* varno, int* vartype, int* varcus);
	int solve_BB(BBrec* BB);
	unsigned char free_BB(BBrec** BB);
	BBrec* pop_BB(BBrec* BB);
	int run_BB(lprec* lp);
}
/** Partial pricing block data */
typedef struct _partialrec {
	lprec *lp;
	int blockcount; /* ## The number of logical blocks or stages in the model */
	int blocknow; /* The currently active block */
	int *blockend; /* Array of column indeces giving the start of each block */
	int *blockpos; /* Array of column indeces giving the start scan position */
	unsigned char isrow;
} partialrec;
typedef struct _SOSgroup SOSgroup;
typedef struct _SOSrec {
	SOSgroup *parent;
	int tagorder;
	char *name;
	int type;
	unsigned char isGUB;
	int size;
	int priority;
	int *members;
	double *weights;
	int *membersSorted;
	int *membersMapped;
} SOSrec;
/** typedef */
struct _SOSgroup {
	lprec *lp; /* Pointer to owner */
	SOSrec **sos_list; /* Array of pointers to SOS lists */
	int sos_alloc; /* Size allocated to specially ordered sets (SOS1, SOS2...) */
	int sos_count; /* Number of specially ordered sets (SOS1, SOS2...) */
	int maxorder; /* The highest-order SOS in the group */
	int sos1_count; /* Number of the lowest order SOS in the group */
	int *membership; /* Array of variable-sorted indeces to SOSes that the variable is member of */
	int *memberpos; /* Starting positions of the each column's membership list */
};
extern ""C"" {
/** SOS storage structure */
	SOSgroup* create_SOSgroup(lprec* lp);
	void resize_SOSgroup(SOSgroup* group);
	int append_SOSgroup(SOSgroup* group, SOSrec* SOS);
	int clean_SOSgroup(SOSgroup* group, unsigned char forceupdatemap);
	void free_SOSgroup(SOSgroup** group);
	SOSrec* create_SOSrec(SOSgroup* group, char* name, int type, int priority, int size, int* variables, double* weights);
	unsigned char delete_SOSrec(SOSgroup* group, int sosindex);
	int append_SOSrec(SOSrec* SOS, int size, int* variables, double* weights);
	void free_SOSrec(SOSrec* SOS);
	/** SOS utilities */
	int make_SOSchain(lprec* lp, unsigned char forceresort);
	int SOS_member_updatemap(SOSgroup* group);
	unsigned char SOS_member_sortlist(SOSgroup* group, int sosindex);
	unsigned char SOS_shift_col(SOSgroup* group, int sosindex, int column, int delta, LLrec* usedmap, unsigned char forceresort);
	int SOS_member_delete(SOSgroup* group, int sosindex, int member);
	int SOS_get_type(SOSgroup* group, int sosindex);
	int SOS_infeasible(SOSgroup* group, int sosindex);
	int SOS_member_index(SOSgroup* group, int sosindex, int member);
	int SOS_member_count(SOSgroup* group, int sosindex);
	int SOS_memberships(SOSgroup* group, int column);
	int* SOS_get_candidates(SOSgroup* group, int sosindex, int column, unsigned char excludetarget, double* upbound, double* lobound);
	int SOS_is_member(SOSgroup* group, int sosindex, int column);
	unsigned char SOS_is_member_of_type(SOSgroup* group, int column, int sostype);
	unsigned char SOS_set_GUB(SOSgroup* group, int sosindex, unsigned char state);
	unsigned char SOS_is_GUB(SOSgroup* group, int sosindex);
	unsigned char SOS_is_marked(SOSgroup* group, int sosindex, int column);
	unsigned char SOS_is_active(SOSgroup* group, int sosindex, int column);
	unsigned char SOS_is_full(SOSgroup* group, int sosindex, int column, unsigned char activeonly);
	unsigned char SOS_can_activate(SOSgroup* group, int sosindex, int column);
	unsigned char SOS_set_marked(SOSgroup* group, int sosindex, int column, unsigned char asactive);
	unsigned char SOS_unmark(SOSgroup* group, int sosindex, int column);
	int SOS_fix_unmarked(SOSgroup* group, int sosindex, int variable, double* bound, double value, unsigned char isupper, int* diffcount, DeltaVrec* changelog);
	int SOS_fix_list(SOSgroup* group, int sosindex, int variable, double* bound, int* varlist, unsigned char isleft, DeltaVrec* changelog);
	int SOS_is_satisfied(SOSgroup* group, int sosindex, double* solution);
	unsigned char SOS_is_feasible(SOSgroup* group, int sosindex, double* solution);
}
/** ------------------------------------------------------------------------- */
typedef int (WINAPI)(lprec* lp, void* userhandle);
typedef void (WINAPI)(lprec* lp, void* userhandle, char* buf);
typedef void (WINAPI)(lprec* lp, void* userhandle, int message);
typedef int (WINAPI)(lprec* lp, void* userhandle, int message);
/** ------------------------------------------------------------------------- */
typedef unsigned char (WINAPI)(lprec* lp, double* column);
typedef unsigned char (WINAPI)(lprec* lp, int count, double* column, int* rowno);
typedef unsigned char (WINAPI)(lprec* lp, double* row, int constr_type, double rh);
typedef unsigned char (WINAPI)(lprec* lp, int count, double* row, int* colno, int constr_type, double rh);
typedef unsigned char (WINAPI)(lprec* lp, double* row, int con_type, double rhs);
typedef int (WINAPI)(lprec* lp, char* name, int sostype, int priority, int count, int* sosvars, double* weights);
typedef int (WINAPI)(lprec* lp, double* column);
typedef lprec *(WINAPI)(lprec* lp);
typedef void (WINAPI)(lprec* lp);
typedef unsigned char (WINAPI)(lprec* lp, int colnr);
typedef unsigned char (WINAPI)(lprec* lp, int rownr);
typedef void (WINAPI)(lprec* lp);
typedef unsigned char (WINAPI)(lprec* lp);
typedef void (WINAPI)(lprec** plp);
typedef int (WINAPI)(lprec* lp);
typedef unsigned char (WINAPI)(lprec* lp, int* bascolumn, unsigned char nonbasic);
typedef int (WINAPI)(lprec* lp);
typedef int (WINAPI)(lprec* lp);
typedef int (WINAPI)(lprec* lp);
typedef int (WINAPI)(lprec* lp);
typedef unsigned char (WINAPI)(lprec* lp);
typedef double (WINAPI)(lprec* lp);
typedef char *(WINAPI)(lprec* lp, int colnr);
typedef unsigned char (WINAPI)(lprec* lp, int colnr, double* column);
typedef int (WINAPI)(lprec* lp, int colnr, double* column, int* nzrow);
typedef int (WINAPI)(lprec* lp, int rownr);
typedef double (WINAPI)(lprec* lp, int rownr, int count, double* primsolution, int* nzindex);
typedef unsigned char (WINAPI)(lprec* lp, double* constr);
typedef unsigned char (WINAPI)(lprec* lp, double* rc);
typedef double (WINAPI)(lprec* lp);
typedef double (WINAPI)(lprec* lp);
typedef double (WINAPI)(lprec* lp);
typedef double (WINAPI)(lprec* lp);
typedef double (WINAPI)(lprec* lp);
typedef double (WINAPI)(lprec* lp);
typedef int (WINAPI)(lprec* lp);
typedef double (WINAPI)(lprec* lp);
typedef unsigned char (WINAPI)(lprec* lp, double* lambda);
typedef double (WINAPI)(lprec* lp, int colnr);
typedef int (WINAPI)(lprec* lp, int orig_index);
typedef char *(WINAPI)(lprec* lp);
typedef int (WINAPI)(lprec* lp);
typedef double (WINAPI)(lprec* lp, int rownr, int colnr);
typedef double (WINAPI)(lprec* lp, int matindex, unsigned char isrow, unsigned char adjustsign);
typedef int (WINAPI)(lprec* lp);
typedef int (WINAPI)(lprec* lp);
typedef double (WINAPI)(lprec* lp, unsigned char absolute);
typedef int (WINAPI)(lprec* lp, unsigned char getabssize);
typedef unsigned char (WINAPI)(lprec* lp, unsigned char isrow);
typedef void (WINAPI)(lprec* lp, unsigned char isrow, unsigned char use_names);
typedef int (WINAPI)(lprec* lp, char* varname, unsigned char isrow);
typedef int (WINAPI)(lprec* lp);
typedef double (WINAPI)(lprec* lp);
typedef int (WINAPI)(lprec* lp);
typedef int (WINAPI)(lprec* lp);
typedef int (WINAPI)(lprec* lp);
typedef int (WINAPI)(lprec* lp);
typedef double (WINAPI)(lprec* lp);
typedef double (WINAPI)(lprec* lp);
typedef int (WINAPI)(lprec* lp, int lp_index);
typedef char *(WINAPI)(lprec* lp, int colnr);
typedef char *(WINAPI)(lprec* lp, int rownr);
typedef void (WINAPI)(lprec* lp, int* blockcount, int* blockstart, unsigned char isrow);
typedef int (WINAPI)(lprec* lp);
typedef int (WINAPI)(lprec* lp);
typedef int (WINAPI)(lprec* lp);
typedef unsigned char (WINAPI)(lprec* lp, double* pv);
typedef int (WINAPI)(lprec* lp);
typedef unsigned char (WINAPI)(lprec* lp, double* clower, double* cupper, int* updatelimit);
typedef unsigned char (WINAPI)(lprec* lp, double** constr);
typedef unsigned char (WINAPI)(lprec* lp, double** rc);
typedef unsigned char (WINAPI)(lprec* lp, double** lambda);
typedef unsigned char (WINAPI)(lprec* lp, double** pv);
typedef unsigned char (WINAPI)(lprec* lp, double** objfrom, double** objtill);
typedef unsigned char (WINAPI)(lprec* lp, double** objfrom, double** objtill, double** objfromvalue, double** objtillvalue);
typedef unsigned char (WINAPI)(lprec* lp, double** duals, double** dualsfrom, double** dualstill);
typedef unsigned char (WINAPI)(lprec* lp, double** var);
typedef double (WINAPI)(lprec* lp, int rownr);
typedef double (WINAPI)(lprec* lp, int rownr);
typedef int (WINAPI)(lprec* lp, int rownr, double* row, int* colno);
typedef unsigned char (WINAPI)(lprec* lp, int rownr, double* row);
typedef char *(WINAPI)(lprec* lp, int rownr);
typedef double (WINAPI)(lprec* lp);
typedef int (WINAPI)(lprec* lp);
typedef unsigned char (WINAPI)(lprec* lp, double* objfrom, double* objtill);
typedef unsigned char (WINAPI)(lprec* lp, double* objfrom, double* objtill, double* objfromvalue, double* objtillvalue);
typedef unsigned char (WINAPI)(lprec* lp, double* duals, double* dualsfrom, double* dualstill);
typedef int (WINAPI)(lprec* lp);
typedef int (WINAPI)(lprec* lp);
typedef int (WINAPI)(lprec* lp);
typedef int (WINAPI)(lprec* lp);
typedef char *(WINAPI)(lprec* lp, int statuscode);
typedef long (WINAPI)(lprec* lp);
typedef long long (WINAPI)(lprec* lp);
typedef long long (WINAPI)(lprec* lp);
typedef double (WINAPI)(lprec* lp, int colnr);
typedef int (WINAPI)(lprec* lp, int colnr);
typedef double (WINAPI)(lprec* lp, int index);
typedef double (WINAPI)(lprec* lp, int index);
typedef int (WINAPI)(lprec* lp, int colnr);
typedef unsigned char (WINAPI)(lprec* lp, double* var);
typedef int (WINAPI)(lprec* lp);
typedef unsigned char (WINAPI)(lprec* lp, double* guessvector, int* basisvector);
typedef double (WINAPI)(lprec* lp);
typedef unsigned char (WINAPI)(lprec* lp);
typedef unsigned char (WINAPI)(lprec* lp);
typedef unsigned char (WINAPI)(lprec* lp);
typedef unsigned char (WINAPI)(lprec* lp, int testmask);
typedef unsigned char (WINAPI)(lprec* lp, int colnr);
typedef unsigned char (WINAPI)(lprec* lp);
typedef unsigned char (WINAPI)(lprec* lp, int rownr, int mask);
typedef unsigned char (WINAPI)(lprec* lp);
typedef unsigned char (WINAPI)(lprec* lp, double* values, double threshold);
typedef unsigned char (WINAPI)(lprec* lp, int colnr);
typedef unsigned char (WINAPI)(lprec* lp, double value);
typedef unsigned char (WINAPI)(lprec* lp, int column);
typedef unsigned char (WINAPI)(lprec* lp);
typedef unsigned char (WINAPI)(lprec* lp);
typedef unsigned char (WINAPI)(lprec* lp);
typedef unsigned char (WINAPI)(lprec* lp);
typedef unsigned char (WINAPI)(lprec* lp);
typedef unsigned char (WINAPI)(lprec* lp, int colnr);
typedef unsigned char (WINAPI)(lprec* lp);
typedef unsigned char (WINAPI)(lprec* lp, int testmask);
typedef unsigned char (WINAPI)(lprec* lp, int rule);
typedef unsigned char (WINAPI)(lprec* lp, int testmask);
typedef unsigned char (WINAPI)(lprec* lp, int testmask);
typedef unsigned char (WINAPI)(lprec* lp, int scaletype);
typedef unsigned char (WINAPI)(lprec* lp, int colnr);
typedef unsigned char (WINAPI)(lprec* lp, int colnr);
typedef unsigned char (WINAPI)(lprec* lp);
typedef void (WINAPI)(int* majorversion, int* minorversion, int* release, int* build);
typedef lprec *(WINAPI)(int rows, int columns);
typedef void (WINAPI)(lprec* lp, int columns);
typedef unsigned char (WINAPI)(lprec* lp, char* filename);
typedef void (WINAPI)(lprec* lp);
typedef void (WINAPI)(lprec* lp);
typedef void (WINAPI)(lprec* lp);
typedef void (WINAPI)(lprec* lp);
typedef void (WINAPI)(lprec* lp, int columns);
typedef void (WINAPI)(lprec* lp, char* str);
typedef void (WINAPI)(lprec* lp);
typedef void (WINAPI)(lprec* lp, lphandle_intfunc newctrlc, void* ctrlchandle);
typedef void (WINAPI)(lprec* lp, lphandleint_intfunc newnode, void* bbnodehandle);
typedef void (WINAPI)(lprec* lp, lphandleint_intfunc newbranch, void* bbbranchhandle);
typedef void (WINAPI)(lprec* lp, lphandlestr_func newlog, void* loghandle);
typedef void (WINAPI)(lprec* lp, lphandleint_func newmsg, void* msghandle, int mask);
typedef lprec *(WINAPI)(char* filename, int verbose, char* lp_name);
typedef lprec *(WINAPI)(char* filename, int options);
typedef lprec *(WINAPI)(char* xliname, char* modelname, char* dataname, char* options, int verbose);
typedef unsigned char (WINAPI)(lprec* lp, char* filename, char* info);
typedef void (WINAPI)(lprec* lp);
typedef unsigned char (WINAPI)(lprec* lp, char* filename, char* options);
typedef void (WINAPI)(lprec* lp);
typedef unsigned char (WINAPI)(lprec* lp, int rows, int columns);
typedef unsigned char (WINAPI)(lprec* lp, unsigned char turnon);
typedef void (WINAPI)(lprec* lp, int anti_degen);
typedef int (WINAPI)(lprec* lp, int basisPos, int enteringCol);
typedef unsigned char (WINAPI)(lprec* lp, int* bascolumn, unsigned char nonbasic);
typedef void (WINAPI)(lprec* lp, int mode);
typedef void (WINAPI)(lprec* lp, int bb_maxlevel);
typedef void (WINAPI)(lprec* lp, int bb_floorfirst);
typedef void (WINAPI)(lprec* lp, int bb_rule);
typedef unsigned char (WINAPI)(lprec* lp, char* filename);
typedef unsigned char (WINAPI)(lprec* lp, int colnr, unsigned char must_be_bin);
typedef unsigned char (WINAPI)(lprec* lp, int colnr, double lower, double upper);
typedef void (WINAPI)(lprec* lp, unsigned char tighten);
typedef void (WINAPI)(lprec* lp, unsigned char break_at_first);
typedef void (WINAPI)(lprec* lp, double break_at_value);
typedef unsigned char (WINAPI)(lprec* lp, int colnr, double* column);
typedef unsigned char (WINAPI)(lprec* lp, int colnr, int count, double* column, int* rowno);
typedef unsigned char (WINAPI)(lprec* lp, int colnr, char* new_name);
typedef unsigned char (WINAPI)(lprec* lp, int rownr, int con_type);
typedef void (WINAPI)(lprec* lp, unsigned char debug);
typedef void (WINAPI)(lprec* lp, double epsb);
typedef void (WINAPI)(lprec* lp, double epsd);
typedef void (WINAPI)(lprec* lp, double epsel);
typedef void (WINAPI)(lprec* lp, double epsint);
typedef unsigned char (WINAPI)(lprec* lp, int epslevel);
typedef void (WINAPI)(lprec* lp, double epsperturb);
typedef void (WINAPI)(lprec* lp, double epspivot);
typedef unsigned char (WINAPI)(lprec* lp, int colnr);
typedef void (WINAPI)(lprec* lp, int improve);
typedef void (WINAPI)(lprec* lp, double infinite);
typedef unsigned char (WINAPI)(lprec* lp, int colnr, unsigned char must_be_int);
typedef void (WINAPI)(lprec* lp, unsigned char lag_trace);
typedef unsigned char (WINAPI)(lprec* lp, int colnr, double value);
typedef unsigned char (WINAPI)(lprec* lp, char* lpname);
typedef unsigned char (WINAPI)(lprec* lp, int row, int column, double value);
typedef void (WINAPI)(lprec* lp);
typedef void (WINAPI)(lprec* lp, int max_num_inv);
typedef void (WINAPI)(lprec* lp);
typedef void (WINAPI)(lprec* lp, unsigned char absolute, double mip_gap);
typedef unsigned char (WINAPI)(lprec* lp, int multiblockdiv);
typedef void (WINAPI)(lprec* lp, double negrange);
typedef unsigned char (WINAPI)(lprec* lp, int colnr, double value);
typedef void (WINAPI)(lprec* lp, double obj_bound);
typedef unsigned char (WINAPI)(lprec* lp, double* row);
typedef unsigned char (WINAPI)(lprec* lp, int count, double* row, int* colno);
typedef void (WINAPI)(lprec* lp, unsigned char obj_in_basis);
typedef unsigned char (WINAPI)(lprec* lp, char* filename);
typedef void (WINAPI)(lprec* lp, FILE* stream);
typedef unsigned char (WINAPI)(lprec* lp, int blockcount, int* blockstart, unsigned char isrow);
typedef void (WINAPI)(lprec* lp, int piv_rule);
typedef void (WINAPI)(lprec* lp, unsigned char dodual);
typedef void (WINAPI)(lprec* lp, int presolvemode, int maxloops);
typedef void (WINAPI)(lprec* lp, int print_sol);
typedef unsigned char (WINAPI)(lprec* lp, double* clower, double* cupper, int* updatelimit);
typedef unsigned char (WINAPI)(lprec* lp, int rownr, double value);
typedef unsigned char (WINAPI)(lprec* lp, int rownr, double deltavalue);
typedef void (WINAPI)(lprec* lp, double* rh);
typedef unsigned char (WINAPI)(lprec* lp, int rownr, double* row);
typedef unsigned char (WINAPI)(lprec* lp, int rownr, int count, double* row, int* colno);
typedef unsigned char (WINAPI)(lprec* lp, int rownr, char* new_name);
typedef void (WINAPI)(lprec* lp, double scalelimit);
typedef void (WINAPI)(lprec* lp, int scalemode);
typedef unsigned char (WINAPI)(lprec* lp, int colnr, unsigned char must_be_sc);
typedef void (WINAPI)(lprec* lp, unsigned char maximize);
typedef void (WINAPI)(lprec* lp, int simplextype);
typedef void (WINAPI)(lprec* lp, int limit);
typedef void (WINAPI)(lprec* lp, long sectimeout);
typedef void (WINAPI)(lprec* lp, unsigned char trace);
typedef unsigned char (WINAPI)(lprec* lp, int colnr, double value);
typedef unsigned char (WINAPI)(lprec* lp, int colnr, int branch_mode);
typedef unsigned char (WINAPI)(lprec* lp, double* weights);
typedef void (WINAPI)(lprec* lp, int verbose);
typedef unsigned char (WINAPI)(lprec* lp, char* filename);
typedef int (WINAPI)(lprec* lp);
typedef unsigned char (WINAPI)(lprec* lp, char* col_string);
typedef unsigned char (WINAPI)(lprec* lp, char* row_string, int constr_type, double rh);
typedef unsigned char (WINAPI)(lprec* lp, char* row_string, int con_type, double rhs);
typedef unsigned char (WINAPI)(lprec* lp, char* row_string);
typedef unsigned char (WINAPI)(lprec* lp, char* rh_string);
typedef double (WINAPI)(lprec* lp);
typedef void (WINAPI)(lprec* lp);
typedef unsigned char (WINAPI)(lprec* lp, char* filename);
typedef unsigned char (WINAPI)(lprec* lp, FILE* output);
typedef unsigned char (WINAPI)(lprec* lp, char* filename);
typedef unsigned char (WINAPI)(lprec* lp, FILE* output);
typedef unsigned char (WINAPI)(lprec* lp, char* filename);
typedef unsigned char (WINAPI)(lprec* lp, FILE* output);
typedef unsigned char (WINAPI)(lprec* lp, char* filename, char* options, unsigned char results);
typedef unsigned char (WINAPI)(lprec* lp, char* filename);
typedef unsigned char (WINAPI)(lprec* lp, char* filename, char* options);
/** ------------------------------------------------------------------------- */
typedef unsigned char (WINAPI)(lprec* lp, int level);
typedef void (__cdecl reportfunc)(lprec* lp, int level, char* format, ...);
typedef char *(__cdecl explainfunc)(lprec* lp, char* format, ...);
typedef int (WINAPI)(lprec* lp, int varin, double* pcol, int* nzlist, int* maxabs);
typedef int (WINAPI)(lprec* lp, int j, int rn, double bj);
typedef double (WINAPI)(lprec* lp, int varnr, double mult);
typedef int (WINAPI)(lprec* lp, unsigned char* usedpos, int* colorder, int* size, unsigned char symmetric);
typedef unsigned char (WINAPI)(lprec* lp, unsigned char shiftbounds, unsigned final char);
typedef void (WINAPI)(int* actionvar, int actionmask);
typedef unsigned char (WINAPI)(int actionvar, int testmask);
typedef void (WINAPI)(int* actionvar, int actionmask);
/** ------------------------------------------------------------------------- */
typedef char *(BFPchar)();
typedef void (BFP_lp)(lprec* lp);
typedef void (BFP_lpint)(lprec* lp, int newsize);
typedef int (BFPint_lp)(lprec* lp);
typedef int (BFPint_lpint)(lprec* lp, int kind);
typedef double (BFPreal_lp)(lprec* lp);
typedef double *(BFPrealp_lp)(lprec* lp);
typedef void (BFP_lpbool)(lprec* lp, unsigned char maximum);
typedef int (BFPint_lpbool)(lprec* lp, unsigned char maximum);
typedef int (BFPint_lpintintboolbool)(lprec* lp, int uservars, int Bsize, unsigned char* usedpos, unsigned final char);
typedef void (BFP_lprealint)(lprec* lp, double* pcol, int* nzidx);
typedef void (BFP_lprealintrealint)(lprec* lp, double* prow, int* pnzidx, double* drow, int* dnzidx);
typedef unsigned char (BFPbool_lp)(lprec* lp);
typedef unsigned char (BFPbool_lpbool)(lprec* lp, unsigned char changesign);
typedef unsigned char (BFPbool_lpint)(lprec* lp, int size);
typedef unsigned char (BFPbool_lpintintchar)(lprec* lp, int size, int deltasize, char* options);
typedef unsigned char (BFPbool_lpintintint)(lprec* lp, int size, int deltasize, int sizeofvar);
typedef double (BFPlreal_lpintintreal)(lprec* lp, int row_nr, int col_nr, double* pcol);
typedef double (BFPreal_lplrealreal)(lprec* lp, double theta, double* pcol);
typedef int (getcolumnex_func)(lprec* lp, int colnr, double* nzvalues, int* nzrows, int* mapin);
typedef int (BFPint_lpintrealcbintint)(lprec* lp, int items, getcolumnex_func cb, int* maprow, int* mapcol);
/** ------------------------------------------------------------------------- */
typedef char *(XLIchar)();
typedef unsigned char (XLIbool_lpintintint)(lprec* lp, int size, int deltasize, int sizevar);
typedef unsigned char (XLIbool_lpcharcharcharint)(lprec* lp, char* modelname, char* dataname, char* options, int verbose);
typedef unsigned char (XLIbool_lpcharcharbool)(lprec* lp, char* filename, char* options, unsigned char results);
/** ------------------------------------------------------------------------- */
struct _lprec {
	/** Full list of exported functions made available in a quasi object-oriented fashion */
	add_column_func *add_column;
	add_columnex_func *add_columnex;
	add_constraint_func *add_constraint;
	add_constraintex_func *add_constraintex;
	add_lag_con_func *add_lag_con;
	add_SOS_func *add_SOS;
	column_in_lp_func *column_in_lp;
	copy_lp_func *copy_lp;
	default_basis_func *default_basis;
	del_column_func *del_column;
	del_constraint_func *del_constraint;
	delete_lp_func *delete_lp;
	dualize_lp_func *dualize_lp;
	free_lp_func *free_lp;
	get_anti_degen_func *get_anti_degen;
	get_basis_func *get_basis;
	get_basiscrash_func *get_basiscrash;
	get_bb_depthlimit_func *get_bb_depthlimit;
	get_bb_floorfirst_func *get_bb_floorfirst;
	get_bb_rule_func *get_bb_rule;
	get_bounds_tighter_func *get_bounds_tighter;
	get_break_at_value_func *get_break_at_value;
	get_col_name_func *get_col_name;
	get_columnex_func *get_columnex;
	get_constr_type_func *get_constr_type;
	get_constr_value_func *get_constr_value;
	get_constraints_func *get_constraints;
	get_dual_solution_func *get_dual_solution;
	get_epsb_func *get_epsb;
	get_epsd_func *get_epsd;
	get_epsel_func *get_epsel;
	get_epsint_func *get_epsint;
	get_epsperturb_func *get_epsperturb;
	get_epspivot_func *get_epspivot;
	get_improve_func *get_improve;
	get_infinite_func *get_infinite;
	get_lambda_func *get_lambda;
	get_lowbo_func *get_lowbo;
	get_lp_index_func *get_lp_index;
	get_lp_name_func *get_lp_name;
	get_Lrows_func *get_Lrows;
	get_mat_func *get_mat;
	get_mat_byindex_func *get_mat_byindex;
	get_max_level_func *get_max_level;
	get_maxpivot_func *get_maxpivot;
	get_mip_gap_func *get_mip_gap;
	get_multiprice_func *get_multiprice;
	get_nameindex_func *get_nameindex;
	get_Ncolumns_func *get_Ncolumns;
	get_negrange_func *get_negrange;
	get_nz_func *get_nonzeros;
	get_Norig_columns_func *get_Norig_columns;
	get_Norig_rows_func *get_Norig_rows;
	get_Nrows_func *get_Nrows;
	get_obj_bound_func *get_obj_bound;
	get_objective_func *get_objective;
	get_orig_index_func *get_orig_index;
	get_origcol_name_func *get_origcol_name;
	get_origrow_name_func *get_origrow_name;
	get_partialprice_func *get_partialprice;
	get_pivoting_func *get_pivoting;
	get_presolve_func *get_presolve;
	get_presolveloops_func *get_presolveloops;
	get_primal_solution_func *get_primal_solution;
	get_print_sol_func *get_print_sol;
	get_pseudocosts_func *get_pseudocosts;
	get_ptr_constraints_func *get_ptr_constraints;
	get_ptr_dual_solution_func *get_ptr_dual_solution;
	get_ptr_lambda_func *get_ptr_lambda;
	get_ptr_primal_solution_func *get_ptr_primal_solution;
	get_ptr_sensitivity_obj_func *get_ptr_sensitivity_obj;
	get_ptr_sensitivity_objex_func *get_ptr_sensitivity_objex;
	get_ptr_sensitivity_rhs_func *get_ptr_sensitivity_rhs;
	get_ptr_variables_func *get_ptr_variables;
	get_rh_func *get_rh;
	get_rh_range_func *get_rh_range;
	get_row_func *get_row;
	get_rowex_func *get_rowex;
	get_row_name_func *get_row_name;
	get_scalelimit_func *get_scalelimit;
	get_scaling_func *get_scaling;
	get_sensitivity_obj_func *get_sensitivity_obj;
	get_sensitivity_objex_func *get_sensitivity_objex;
	get_sensitivity_rhs_func *get_sensitivity_rhs;
	get_simplextype_func *get_simplextype;
	get_solutioncount_func *get_solutioncount;
	get_solutionlimit_func *get_solutionlimit;
	get_status_func *get_status;
	get_statustext_func *get_statustext;
	get_timeout_func *get_timeout;
	get_total_iter_func *get_total_iter;
	get_total_nodes_func *get_total_nodes;
	get_upbo_func *get_upbo;
	get_var_branch_func *get_var_branch;
	get_var_dualresult_func *get_var_dualresult;
	get_var_primalresult_func *get_var_primalresult;
	get_var_priority_func *get_var_priority;
	get_variables_func *get_variables;
	get_verbose_func *get_verbose;
	get_working_objective_func *get_working_objective;
	has_BFP_func *has_BFP;
	has_XLI_func *has_XLI;
	is_add_rowmode_func *is_add_rowmode;
	is_anti_degen_func *is_anti_degen;
	is_binary_func *is_binary;
	is_break_at_first_func *is_break_at_first;
	is_constr_type_func *is_constr_type;
	is_debug_func *is_debug;
	is_feasible_func *is_feasible;
	is_infinite_func *is_infinite;
	is_int_func *is_int;
	is_integerscaling_func *is_integerscaling;
	is_lag_trace_func *is_lag_trace;
	is_maxim_func *is_maxim;
	is_nativeBFP_func *is_nativeBFP;
	is_nativeXLI_func *is_nativeXLI;
	is_negative_func *is_negative;
	is_obj_in_basis_func *is_obj_in_basis;
	is_piv_mode_func *is_piv_mode;
	is_piv_rule_func *is_piv_rule;
	is_presolve_func *is_presolve;
	is_scalemode_func *is_scalemode;
	is_scaletype_func *is_scaletype;
	is_semicont_func *is_semicont;
	is_SOS_var_func *is_SOS_var;
	is_trace_func *is_trace;
	is_unbounded_func *is_unbounded;
	is_use_names_func *is_use_names;
	lp_solve_version_func *lp_solve_version;
	make_lp_func *make_lp;
	print_constraints_func *print_constraints;
	print_debugdump_func *print_debugdump;
	print_duals_func *print_duals;
	print_lp_func *print_lp;
	print_objective_func *print_objective;
	print_scales_func *print_scales;
	print_solution_func *print_solution;
	print_str_func *print_str;
	print_tableau_func *print_tableau;
	put_abortfunc_func *put_abortfunc;
	put_bb_nodefunc_func *put_bb_nodefunc;
	put_bb_branchfunc_func *put_bb_branchfunc;
	put_logfunc_func *put_logfunc;
	put_msgfunc_func *put_msgfunc;
	read_LP_func *read_LP;
	read_MPS_func *read_MPS;
	read_XLI_func *read_XLI;
	read_params_func *read_params;
	read_basis_func *read_basis;
	reset_basis_func *reset_basis;
	reset_params_func *reset_params;
	resize_lp_func *resize_lp;
	set_add_rowmode_func *set_add_rowmode;
	set_anti_degen_func *set_anti_degen;
	set_basisvar_func *set_basisvar;
	set_basis_func *set_basis;
	set_basiscrash_func *set_basiscrash;
	set_bb_depthlimit_func *set_bb_depthlimit;
	set_bb_floorfirst_func *set_bb_floorfirst;
	set_bb_rule_func *set_bb_rule;
	set_BFP_func *set_BFP;
	set_binary_func *set_binary;
	set_bounds_func *set_bounds;
	set_bounds_tighter_func *set_bounds_tighter;
	set_break_at_first_func *set_break_at_first;
	set_break_at_value_func *set_break_at_value;
	set_column_func *set_column;
	set_columnex_func *set_columnex;
	set_col_name_func *set_col_name;
	set_constr_type_func *set_constr_type;
	set_debug_func *set_debug;
	set_epsb_func *set_epsb;
	set_epsd_func *set_epsd;
	set_epsel_func *set_epsel;
	set_epsint_func *set_epsint;
	set_epslevel_func *set_epslevel;
	set_epsperturb_func *set_epsperturb;
	set_epspivot_func *set_epspivot;
	set_unbounded_func *set_unbounded;
	set_improve_func *set_improve;
	set_infinite_func *set_infinite;
	set_int_func *set_int;
	set_lag_trace_func *set_lag_trace;
	set_lowbo_func *set_lowbo;
	set_lp_name_func *set_lp_name;
	set_mat_func *set_mat;
	set_maxim_func *set_maxim;
	set_maxpivot_func *set_maxpivot;
	set_minim_func *set_minim;
	set_mip_gap_func *set_mip_gap;
	set_multiprice_func *set_multiprice;
	set_negrange_func *set_negrange;
	set_obj_bound_func *set_obj_bound;
	set_obj_fn_func *set_obj_fn;
	set_obj_fnex_func *set_obj_fnex;
	set_obj_func *set_obj;
	set_obj_in_basis_func *set_obj_in_basis;
	set_outputfile_func *set_outputfile;
	set_outputstream_func *set_outputstream;
	set_partialprice_func *set_partialprice;
	set_pivoting_func *set_pivoting;
	set_preferdual_func *set_preferdual;
	set_presolve_func *set_presolve;
	set_print_sol_func *set_print_sol;
	set_pseudocosts_func *set_pseudocosts;
	set_rh_func *set_rh;
	set_rh_range_func *set_rh_range;
	set_rh_vec_func *set_rh_vec;
	set_row_func *set_row;
	set_rowex_func *set_rowex;
	set_row_name_func *set_row_name;
	set_scalelimit_func *set_scalelimit;
	set_scaling_func *set_scaling;
	set_semicont_func *set_semicont;
	set_sense_func *set_sense;
	set_simplextype_func *set_simplextype;
	set_solutionlimit_func *set_solutionlimit;
	set_timeout_func *set_timeout;
	set_trace_func *set_trace;
	set_upbo_func *set_upbo;
	set_use_names_func *set_use_names;
	set_var_branch_func *set_var_branch;
	set_var_weights_func *set_var_weights;
	set_verbose_func *set_verbose;
	set_XLI_func *set_XLI;
	solve_func *solve;
	str_add_column_func *str_add_column;
	str_add_constraint_func *str_add_constraint;
	str_add_lag_con_func *str_add_lag_con;
	str_set_obj_fn_func *str_set_obj_fn;
	str_set_rh_vec_func *str_set_rh_vec;
	time_elapsed_func *time_elapsed;
	unscale_func *unscale;
	write_lp_func *write_lp;
	write_LP_func *write_LP;
	write_mps_func *write_mps;
	write_MPS_func *write_MPS;
	write_freemps_func *write_freemps;
	write_freeMPS_func *write_freeMPS;
	write_XLI_func *write_XLI;
	write_basis_func *write_basis;
	write_params_func *write_params;
	/** Spacer */
	int *alignmentspacer;
	/** Problem description */
	char *lp_name; /* The name of the model */
	/** Problem sizes */
	int sum; /* The total number of variables, including slacks */
	int rows;
	int columns;
	int equalities; /* No of non-Lagrangean equality constraints in the problem */
	int boundedvars; /* Count of bounded variables */
	int INTfuture1;
	/** Memory allocation sizes */
	int sum_alloc; /* The allocated memory for row+column-sized data */
	int rows_alloc; /* The allocated memory for row-sized data */
	int columns_alloc; /* The allocated memory for column-sized data */
	/** Model status and solver result variables */
	unsigned char source_is_file; /* The base model was read from a file */
	unsigned char model_is_pure; /* The model has been built entirely from row and column additions */
	unsigned char model_is_valid; /* Has this lp pased the 'test' */
	unsigned char tighten_on_set; /* Specify if bounds will be tightened or overriden at bound setting */
	unsigned char names_used; /* Flag to indicate if names for rows and columns are used */
	unsigned char use_row_names; /* Flag to indicate if names for rows are used */
	unsigned char use_col_names; /* Flag to indicate if names for columns are used */
	unsigned char lag_trace; /* Print information on Lagrange progression */
	unsigned char spx_trace; /* Print information on simplex progression */
	unsigned char bb_trace; /* TRUE to print extra debug information */
	unsigned char streamowned; /* TRUE if the handle should be closed at delete_lp() */
	unsigned char obj_in_basis; /* TRUE if the objective function is in the basis matrix */
	int spx_status; /* Simplex solver feasibility/mode code */
	int lag_status; /* Extra status variable for lag_solve */
	int solutioncount; /* number of equal-valued solutions found (up to solutionlimit) */
	int solutionlimit; /* upper number of equal-valued solutions kept track of */
	double real_solution; /* Optimal non-MIP solution base */
	double *solution; /* sum_alloc+1 : Solution array of the next to optimal LP,
                                   Index   0           : Objective function value,
                                   Indeces 1..rows     : Slack variable values,
                                   Indeced rows+1..sum : Variable values */
	double *best_solution; /* sum_alloc+1 : Solution array of optimal 'Integer' LP,
                                   structured as the solution array above */
	double *full_solution; /* sum_alloc+1 : Final solution array expanded for deleted variables */
	double *edgeVector; /* Array of reduced cost scaling norms (DEVEX and Steepest Edge) */
	double *drow; /* sum+1: Reduced costs of the last simplex */
	int *nzdrow; /* sum+1: Indeces of non-zero reduced costs of the last simplex */
	double *duals; /* rows_alloc+1 : The dual variables of the last LP */
	double *full_duals; /* sum_alloc+1: Final duals array expanded for deleted variables */
	double *dualsfrom; /* sum_alloc+1 :The sensitivity on dual variables/reduced costs
                                   of the last LP */
	double *dualstill; /* sum_alloc+1 :The sensitivity on dual variables/reduced costs
                                   of the last LP */
	double *objfrom; /* columns_alloc+1 :The sensitivity on objective function
                                   of the last LP */
	double *objtill; /* columns_alloc+1 :The sensitivity on objective function
                                   of the last LP */
	double *objfromvalue; /* columns_alloc+1 :The value of the variables when objective value
                                   is at its from value of the last LP */
	double *orig_obj; /* Unused pointer - Placeholder for OF not part of B */
	double *obj; /* Special vector used to temporarily change the OF vector */
	long long current_iter; /* Number of iterations in the current/last simplex */
	long long total_iter; /* Number of iterations over all B&B steps */
	long long current_bswap; /* Number of bound swaps in the current/last simplex */
	long long total_bswap; /* Number of bount swaps over all B&B steps */
	int solvecount; /* The number of solve() performed in this model */
	int max_pivots; /* Number of pivots between refactorizations of the basis */
	/** Various execution parameters */
	int simplex_strategy; /* Set desired combination of primal and dual simplex algorithms */
	int simplex_mode; /* Specifies the current simplex mode during solve; see simplex_strategy */
	int verbose; /* Set amount of run-time messages and results */
	int print_sol; /* TRUE to print optimal solution; AUTOMATIC skips zeros */
	FILE *outstream; /* Output stream, initialized to STDOUT */
	/** Main Branch and Bound settings */
	unsigned char *bb_varbranch; /* Determines branching strategy at the individual variable level;
                                   the setting here overrides the bb_floorfirst setting */
	int piv_strategy; /* Strategy for selecting row and column entering/leaving */
	int _piv_rule_; /* Internal working rule-part of piv_strategy above */
	int bb_rule; /* Rule for selecting B&B variables */
	unsigned char bb_floorfirst; /* Set BRANCH_FLOOR for B&B to set variables to floor bound first;
                                   conversely with BRANCH_CEILING, the ceiling value is set first */
	unsigned char bb_breakfirst; /* TRUE to stop at first feasible solution */
	unsigned char _piv_left_; /* Internal variable indicating active pricing loop order */
	unsigned char BOOLfuture1;
	double scalelimit; /* Relative convergence criterion for iterated scaling */
	int scalemode; /* OR-ed codes for data scaling */
	int improve; /* Set to non-zero for iterative improvement */
	int anti_degen; /* Anti-degen strategy (or none) TRUE to avoid cycling */
	int do_presolve; /* PRESOLVE_ parameters for LP presolving */
	int presolveloops; /* Maximum number of presolve loops */
	int perturb_count; /* The number of bound relaxation retries performed */
	/** Row and column names storage variables */
	hashelem **row_name; /* rows_alloc+1 */
	hashelem **col_name; /* columns_alloc+1 */
	hashtable *rowname_hashtab; /* hash table to store row names */
	hashtable *colname_hashtab; /* hash table to store column names */
	/** Optionally specify continuous rows/column blocks for partial pricing */
	partialrec *rowblocks;
	partialrec *colblocks;
	/** Row and column type codes */
	unsigned char *var_type; /* sum_alloc+1 : TRUE if variable must be integer */
	/** Data for multiple pricing */
	multirec *multivars;
	int multiblockdiv; /* The divisor used to set or augment pricing block */
	/** Variable (column) parameters */
	int fixedvars; /* The current number of basic fixed variables in the model */
	int int_vars; /* Number of variables required to be integer */
	int sc_vars; /* Number of semi-continuous variables */
	double *sc_lobound; /* sum_columns+1 : TRUE if variable is semi-continuous;
                                   value replaced by conventional lower bound during solve */
	int *var_is_free; /* columns+1: Index of twin variable if variable is free */
	int *var_priority; /* columns: Priority-mapping of variables */
	SOSgroup *GUB; /* Pointer to record containing GUBs */
	int sos_vars; /* Number of variables in the sos_priority list */
	int sos_ints; /* Number of integers in SOS'es above */
	SOSgroup *SOS; /* Pointer to record containing all SOS'es */
	int *sos_priority; /* Priority-sorted list of variables (no duplicates) */
	/** Optionally specify list of active rows/columns used in multiple pricing */
	double *bsolveVal; /* rows+1: bsolved solution vector for reduced costs */
	int *bsolveIdx; /* rows+1: Non-zero indeces of bsolveVal */
	/** RHS storage */
	double *orig_rhs; /* rows_alloc+1 : The RHS after scaling and sign
                                   changing, but before 'Bound transformation' */
	double *rhs; /* rows_alloc+1 : The RHS of the current simplex tableau */
	/** Row (constraint) parameters */
	int *row_type; /* rows_alloc+1 : Row/constraint type coding */
	/** Optionally specify data for dual long-step */
	multirec *longsteps;
	/** Original and working row and variable bounds */
	double *orig_upbo; /* sum_alloc+1 : Bound before transformations */
	double *upbo; /*  " " : Upper bound after transformation and B&B work */
	double *orig_lowbo; /*  "       "                                 */
	double *lowbo; /*  " " : Lower bound after transformation and B&B work */
	/** User data and basis factorization matrices (ETA or LU, product form) */
	MATrec *matA;
	INVrec *invB;
	/** Basis and bounds */
	BBrec *bb_bounds; /* The linked list of B&B bounds */
	BBrec *rootbounds; /* The bounds at the lowest B&B level */
	basisrec *bb_basis; /* The linked list of B&B bases */
	basisrec *rootbasis;
	OBJmonrec *monitor; /* Objective monitoring record for stalling/degeneracy handling */
	/** Scaling parameters */
	double *scalars; /* sum_alloc+1:0..Rows the scaling of the rows,
                                   Rows+1..Sum the scaling of the columns */
	unsigned char scaling_used; /* TRUE if scaling is used */
	unsigned char columns_scaled; /* TRUE if the columns are scaled too */
	unsigned char varmap_locked; /* Determines whether the var_to_orig and orig_to_var are fixed */
	/** Variable state information */
	unsigned char basis_valid; /* TRUE is the basis is still valid */
	int crashmode; /* Basis crashing mode (or none) */
	int *var_basic; /* rows_alloc+1: The list of columns in the basis */
	double *val_nonbasic; /* Array to store current values of non-basic variables */
	unsigned char *is_basic; /* sum_alloc+1: TRUE if the column is in the basis */
	unsigned char *is_lower; /*  "       " : TRUE if the variable is at its
                                   lower bound (or in the basis), FALSE otherwise */
	/** Simplex basis indicators */
	int *rejectpivot; /* List of unacceptable pivot choices due to division-by-zero */
	BBPSrec *bb_PseudoCost; /* Data structure for costing of node branchings */
	int bb_PseudoUpdates; /* Maximum number of updates for pseudo-costs */
	int bb_strongbranches; /* The number of strong B&B branches performed */
	int is_strongbranch; /* Are we currently in a strong branch mode? */
	int bb_improvements; /* The number of discrete B&B objective improvement steps */
	/** Solver working variables */
	double rhsmax; /* The maximum |value| of the rhs vector at any iteration */
	double suminfeas; /* The working sum of primal and dual infeasibilities */
	double bigM; /* Original objective weighting in primal phase 1 */
	double P1extraVal; /* Phase 1 OF/RHS offset for feasibility */
	int P1extraDim; /* Phase 1 additional columns/rows for feasibility */
	int spx_action; /* ACTION_ variables for the simplex routine */
	unsigned char spx_perturbed; /* The variable bounds were relaxed/perturbed into this simplex */
	unsigned char bb_break; /* Solver working variable; signals break of the B&B */
	unsigned char wasPreprocessed; /* The solve preprocessing was performed */
	unsigned char wasPresolved; /* The solve presolver was invoked */
	int INTfuture2;
	/** Lagragean solver storage and parameters */
	MATrec *matL;
	double *lag_rhs; /* Array of Lagrangean rhs vector */
	int *lag_con_type; /* Array of GT, LT or EQ */
	double *lambda; /* Lambda values (Lagrangean multipliers) */
	double lag_bound; /* The Lagrangian lower OF bound */
	double lag_accept; /* The Lagrangian convergence criterion */
	/** Solver thresholds */
	double infinite; /* Limit for dynamic range */
	double negrange; /* Limit for negative variable range */
	double epsmachine; /* Default machine accuracy */
	double epsvalue; /* Input data precision / rounding of data values to 0 */
	double epsprimal; /* For rounding RHS values to 0/infeasibility */
	double epsdual; /* For rounding reduced costs to zero */
	double epspivot; /* Pivot reject tolerance */
	double epsperturb; /* Perturbation scalar */
	double epssolution; /* The solution tolerance for final validation */
	/** Branch & Bound working parameters */
	int bb_status; /* Indicator that the last solvelp() gave an improved B&B solution */
	int bb_level; /* Solver B&B working variable (recursion depth) */
	int bb_maxlevel; /* The deepest B&B level of the last solution */
	int bb_limitlevel; /* The maximum B&B level allowed */
	long long bb_totalnodes; /* Total number of nodes processed in B&B */
	int bb_solutionlevel; /* The B&B level of the last / best solution */
	int bb_cutpoolsize; /* Size of the B&B cut pool */
	int bb_cutpoolused; /* Currently used cut pool */
	int bb_constraintOF; /* General purpose B&B parameter (typically for testing) */
	int *bb_cuttype; /* The type of the currently used cuts */
	int *bb_varactive; /* The B&B state of the variable; 0 means inactive */
	DeltaVrec *bb_upperchange; /* Changes to upper bounds during the B&B phase */
	DeltaVrec *bb_lowerchange; /* Changes to lower bounds during the B&B phase */
	double bb_deltaOF; /* Minimum OF step value; computed at beginning of solve() */
	double bb_breakOF; /* User-settable value for the objective function deemed
                               to be sufficiently good in an integer problem */
	double bb_limitOF; /* "Dual" bound / limit to final optimal MIP solution */
	double bb_heuristicOF; /* Set initial "at least better than" guess for objective function
                               (can significantly speed up B&B iterations) */
	double bb_parentOF; /* The OF value of the previous BB simplex */
	double bb_workOF; /* The unadjusted OF value for the current best solution */
	/** Internal work arrays allocated as required */
	presolveundorec *presolve_undo;
	workarraysrec *workarrays;
	/** MIP parameters */
	double epsint; /* Margin of error in determining if a float value is integer */
	double mip_absgap; /* Absolute MIP gap */
	double mip_relgap; /* Relative MIP gap */
	/** Time/timer variables and extended status text */
	double timecreate;
	double timestart;
	double timeheuristic;
	double timepresolved;
	double timeend;
	long sectimeout;
	/** Extended status message text set via explain() */
	char *ex_status;
	void *hBFP;
	BFPchar *bfp_name;
	BFPbool_lpintintint *bfp_compatible;
	BFPbool_lpintintchar *bfp_init;
	BFP_lp *bfp_free;
	BFPbool_lpint *bfp_resize;
	BFPint_lp *bfp_memallocated;
	BFPbool_lp *bfp_restart;
	BFPbool_lp *bfp_mustrefactorize;
	BFPint_lp *bfp_preparefactorization;
	BFPint_lpintintboolbool *bfp_factorize;
	BFP_lp *bfp_finishfactorization;
	BFP_lp *bfp_updaterefactstats;
	BFPlreal_lpintintreal *bfp_prepareupdate;
	BFPreal_lplrealreal *bfp_pivotRHS;
	BFPbool_lpbool *bfp_finishupdate;
	BFP_lprealint *bfp_ftran_prepare;
	BFP_lprealint *bfp_ftran_normal;
	BFP_lprealint *bfp_btran_normal;
	BFP_lprealintrealint *bfp_btran_double;
	BFPint_lp *bfp_status;
	BFPint_lpbool *bfp_nonzeros;
	BFPbool_lp *bfp_implicitslack;
	BFPint_lp *bfp_indexbase;
	BFPint_lp *bfp_rowoffset;
	BFPint_lp *bfp_pivotmax;
	BFPbool_lpint *bfp_pivotalloc;
	BFPint_lp *bfp_colcount;
	BFPbool_lp *bfp_canresetbasis;
	BFPreal_lp *bfp_efficiency;
	BFPrealp_lp *bfp_pivotvector;
	BFPint_lp *bfp_pivotcount;
	BFPint_lpint *bfp_refactcount;
	BFPbool_lp *bfp_isSetI;
	BFPint_lpintrealcbintint *bfp_findredundant;
	void *hXLI;
	XLIchar *xli_name;
	XLIbool_lpintintint *xli_compatible;
	XLIbool_lpcharcharcharint *xli_readmodel;
	XLIbool_lpcharcharbool *xli_writemodel;
	/** Miscellaneous internal functions made available externally */
	userabortfunc *userabort;
	reportfunc *report;
	explainfunc *explain;
	getvectorfunc *get_lpcolumn;
	getpackedfunc *get_basiscolumn;
	get_OF_activefunc *get_OF_active;
	getMDOfunc *getMDO;
	invertfunc *invert;
	set_actionfunc *set_action;
	is_actionfunc *is_action;
	clear_actionfunc *clear_action;
	/** User program interface callbacks */
	lphandle_intfunc *ctrlc;
	void *ctrlchandle; /* User-specified "owner process ID" */
	lphandlestr_func *writelog;
	void *loghandle; /* User-specified "owner process ID" */
	lphandlestr_func *debuginfo;
	lphandleint_func *usermessage;
	int msgmask;
	void *msghandle; /* User-specified "owner process ID" */
	lphandleint_intfunc *bb_usenode;
	void *bb_nodehandle; /* User-specified "owner process ID" */
	lphandleint_intfunc *bb_usebranch;
	void *bb_branchhandle; /* User-specified "owner process ID" */
	/** replacement of static variables */
	char *rowcol_name; /* The name of a row/column */
};
WINAPI lp_solve_version(int* majorversion, int* minorversion, int* release, int* build);
WINAPI make_lp(int rows, int columns);
WINAPI resize_lp(lprec* lp, int rows, int columns);
WINAPI get_status(lprec* lp);
WINAPI get_statustext(lprec* lp, int statuscode);
WINAPI is_obj_in_basis(lprec* lp);
WINAPI set_obj_in_basis(lprec* lp, unsigned char obj_in_basis);
WINAPI copy_lp(lprec* lp);
WINAPI dualize_lp(lprec* lp);
unsigned char memopt_lp(lprec* lp, int rowextra, int colextra, int nzextra);
WINAPI delete_lp(lprec* lp);
WINAPI free_lp(lprec** plp);
WINAPI set_lp_name(lprec* lp, char* lpname);
WINAPI get_lp_name(lprec* lp);
WINAPI has_BFP(lprec* lp);
WINAPI is_nativeBFP(lprec* lp);
WINAPI set_BFP(lprec* lp, char* filename);
WINAPI read_XLI(char* xliname, char* modelname, char* dataname, char* options, int verbose);
WINAPI write_XLI(lprec* lp, char* filename, char* options, unsigned char results);
WINAPI has_XLI(lprec* lp);
WINAPI is_nativeXLI(lprec* lp);
WINAPI set_XLI(lprec* lp, char* filename);
WINAPI set_obj(lprec* lp, int colnr, double value);
WINAPI set_obj_fn(lprec* lp, double* row);
WINAPI set_obj_fnex(lprec* lp, int count, double* row, int* colno);
WINAPI str_set_obj_fn(lprec* lp, char* row_string);
WINAPI set_sense(lprec* lp, unsigned char maximize);
WINAPI set_maxim(lprec* lp);
WINAPI set_minim(lprec* lp);
WINAPI is_maxim(lprec* lp);
WINAPI add_constraint(lprec* lp, double* row, int constr_type, double rh);
WINAPI add_constraintex(lprec* lp, int count, double* row, int* colno, int constr_type, double rh);
WINAPI set_add_rowmode(lprec* lp, unsigned char turnon);
WINAPI is_add_rowmode(lprec* lp);
WINAPI str_add_constraint(lprec* lp, char* row_string, int constr_type, double rh);
WINAPI set_row(lprec* lp, int rownr, double* row);
WINAPI set_rowex(lprec* lp, int rownr, int count, double* row, int* colno);
WINAPI get_row(lprec* lp, int rownr, double* row);
WINAPI get_rowex(lprec* lp, int rownr, double* row, int* colno);
WINAPI del_constraint(lprec* lp, int rownr);
unsigned char del_constraintex(lprec* lp, LLrec* rowmap);
WINAPI add_lag_con(lprec* lp, double* row, int con_type, double rhs);
WINAPI str_add_lag_con(lprec* lp, char* row_string, int con_type, double rhs);
WINAPI set_lag_trace(lprec* lp, unsigned char lag_trace);
WINAPI is_lag_trace(lprec* lp);
WINAPI set_constr_type(lprec* lp, int rownr, int con_type);
WINAPI get_constr_type(lprec* lp, int rownr);
WINAPI get_constr_value(lprec* lp, int rownr, int count, double* primsolution, int* nzindex);
WINAPI is_constr_type(lprec* lp, int rownr, int mask);
char* get_str_constr_type(lprec* lp, int con_type);
int get_constr_class(lprec* lp, int rownr);
char* get_str_constr_class(lprec* lp, int con_class);
WINAPI set_rh(lprec* lp, int rownr, double value);
WINAPI get_rh(lprec* lp, int rownr);
WINAPI set_rh_range(lprec* lp, int rownr, double deltavalue);
WINAPI get_rh_range(lprec* lp, int rownr);
WINAPI set_rh_vec(lprec* lp, double* rh);
WINAPI str_set_rh_vec(lprec* lp, char* rh_string);
WINAPI add_column(lprec* lp, double* column);
WINAPI add_columnex(lprec* lp, int count, double* column, int* rowno);
WINAPI str_add_column(lprec* lp, char* col_string);
WINAPI set_column(lprec* lp, int colnr, double* column);
WINAPI set_columnex(lprec* lp, int colnr, int count, double* column, int* rowno);
WINAPI column_in_lp(lprec* lp, double* column);
WINAPI get_columnex(lprec* lp, int colnr, double* column, int* nzrow);
WINAPI get_column(lprec* lp, int colnr, double* column);
WINAPI del_column(lprec* lp, int colnr);
unsigned char del_columnex(lprec* lp, LLrec* colmap);
WINAPI set_mat(lprec* lp, int rownr, int colnr, double value);
WINAPI get_mat(lprec* lp, int rownr, int colnr);
WINAPI get_mat_byindex(lprec* lp, int matindex, unsigned char isrow, unsigned char adjustsign);
WINAPI get_nonzeros(lprec* lp);
WINAPI set_bounds_tighter(lprec* lp, unsigned char tighten);
unsigned char get_bounds(lprec* lp, int column, double* lower, double* upper);
WINAPI get_bounds_tighter(lprec* lp);
WINAPI set_upbo(lprec* lp, int colnr, double value);
WINAPI get_upbo(lprec* lp, int colnr);
WINAPI set_lowbo(lprec* lp, int colnr, double value);
WINAPI get_lowbo(lprec* lp, int colnr);
WINAPI set_bounds(lprec* lp, int colnr, double lower, double upper);
WINAPI set_unbounded(lprec* lp, int colnr);
WINAPI is_unbounded(lprec* lp, int colnr);
WINAPI set_int(lprec* lp, int colnr, unsigned char must_be_int);
WINAPI is_int(lprec* lp, int colnr);
WINAPI set_binary(lprec* lp, int colnr, unsigned char must_be_bin);
WINAPI is_binary(lprec* lp, int colnr);
WINAPI set_semicont(lprec* lp, int colnr, unsigned char must_be_sc);
WINAPI is_semicont(lprec* lp, int colnr);
WINAPI is_negative(lprec* lp, int colnr);
WINAPI set_var_weights(lprec* lp, double* weights);
WINAPI get_var_priority(lprec* lp, int colnr);
WINAPI set_pseudocosts(lprec* lp, double* clower, double* cupper, int* updatelimit);
WINAPI get_pseudocosts(lprec* lp, double* clower, double* cupper, int* updatelimit);
WINAPI add_SOS(lprec* lp, char* name, int sostype, int priority, int count, int* sosvars, double* weights);
WINAPI is_SOS_var(lprec* lp, int colnr);
WINAPI set_row_name(lprec* lp, int rownr, char* new_name);
WINAPI get_row_name(lprec* lp, int rownr);
WINAPI get_origrow_name(lprec* lp, int rownr);
WINAPI set_col_name(lprec* lp, int colnr, char* new_name);
WINAPI get_col_name(lprec* lp, int colnr);
WINAPI get_origcol_name(lprec* lp, int colnr);
WINAPI unscale(lprec* lp);
WINAPI set_preferdual(lprec* lp, unsigned char dodual);
WINAPI set_simplextype(lprec* lp, int simplextype);
WINAPI get_simplextype(lprec* lp);
WINAPI default_basis(lprec* lp);
WINAPI set_basiscrash(lprec* lp, int mode);
WINAPI get_basiscrash(lprec* lp);
WINAPI set_basisvar(lprec* lp, int basisPos, int enteringCol);
WINAPI set_basis(lprec* lp, int* bascolumn, unsigned char nonbasic);
WINAPI get_basis(lprec* lp, int* bascolumn, unsigned char nonbasic);
WINAPI reset_basis(lprec* lp);
WINAPI guess_basis(lprec* lp, double* guessvector, int* basisvector);
WINAPI is_feasible(lprec* lp, double* values, double threshold);
WINAPI solve(lprec* lp);
WINAPI time_elapsed(lprec* lp);
WINAPI put_bb_nodefunc(lprec* lp, lphandleint_intfunc newnode, void* bbnodehandle);
WINAPI put_bb_branchfunc(lprec* lp, lphandleint_intfunc newbranch, void* bbbranchhandle);
WINAPI put_abortfunc(lprec* lp, lphandle_intfunc newctrlc, void* ctrlchandle);
WINAPI put_logfunc(lprec* lp, lphandlestr_func newlog, void* loghandle);
WINAPI put_msgfunc(lprec* lp, lphandleint_func newmsg, void* msghandle, int mask);
WINAPI get_primal_solution(lprec* lp, double* pv);
WINAPI get_ptr_primal_solution(lprec* lp, double** pv);
WINAPI get_dual_solution(lprec* lp, double* rc);
WINAPI get_ptr_dual_solution(lprec* lp, double** rc);
WINAPI get_lambda(lprec* lp, double* lambda);
WINAPI get_ptr_lambda(lprec* lp, double** lambda);
WINAPI read_MPS(char* filename, int options);
WINAPI read_mps(FILE* filename, int options);
WINAPI read_freeMPS(char* filename, int options);
WINAPI read_freemps(FILE* filename, int options);
WINAPI write_mps(lprec* lp, char* filename);
WINAPI write_MPS(lprec* lp, FILE* output);
WINAPI write_freemps(lprec* lp, char* filename);
WINAPI write_freeMPS(lprec* lp, FILE* output);
WINAPI write_lp(lprec* lp, char* filename);
WINAPI write_LP(lprec* lp, FILE* output);
WINAPI LP_readhandle(lprec** lp, FILE* filename, int verbose, char* lp_name);
WINAPI read_lp(FILE* filename, int verbose, char* lp_name);
WINAPI read_LP(char* filename, int verbose, char* lp_name);
WINAPI write_basis(lprec* lp, char* filename);
WINAPI read_basis(lprec* lp, char* filename, char* info);
WINAPI write_params(lprec* lp, char* filename, char* options);
WINAPI read_params(lprec* lp, char* filename, char* options);
WINAPI reset_params(lprec* lp);
WINAPI print_lp(lprec* lp);
WINAPI print_tableau(lprec* lp);
WINAPI print_objective(lprec* lp);
WINAPI print_solution(lprec* lp, int columns);
WINAPI print_constraints(lprec* lp, int columns);
WINAPI print_duals(lprec* lp);
WINAPI print_scales(lprec* lp);
WINAPI print_str(lprec* lp, char* str);
WINAPI set_outputstream(lprec* lp, FILE* stream);
WINAPI set_outputfile(lprec* lp, char* filename);
WINAPI set_verbose(lprec* lp, int verbose);
WINAPI get_verbose(lprec* lp);
WINAPI set_timeout(lprec* lp, long sectimeout);
long WINAPI get_timeout(lprec* lp);
WINAPI set_print_sol(lprec* lp, int print_sol);
WINAPI get_print_sol(lprec* lp);
WINAPI set_debug(lprec* lp, unsigned char debug);
WINAPI is_debug(lprec* lp);
WINAPI set_trace(lprec* lp, unsigned char trace);
WINAPI is_trace(lprec* lp);
WINAPI print_debugdump(lprec* lp, char* filename);
WINAPI set_anti_degen(lprec* lp, int anti_degen);
WINAPI get_anti_degen(lprec* lp);
WINAPI is_anti_degen(lprec* lp, int testmask);
WINAPI set_presolve(lprec* lp, int presolvemode, int maxloops);
WINAPI get_presolve(lprec* lp);
WINAPI get_presolveloops(lprec* lp);
WINAPI is_presolve(lprec* lp, int testmask);
WINAPI get_orig_index(lprec* lp, int lp_index);
WINAPI get_lp_index(lprec* lp, int orig_index);
WINAPI set_maxpivot(lprec* lp, int max_num_inv);
WINAPI get_maxpivot(lprec* lp);
WINAPI set_obj_bound(lprec* lp, double obj_bound);
WINAPI get_obj_bound(lprec* lp);
WINAPI set_mip_gap(lprec* lp, unsigned char absolute, double mip_gap);
WINAPI get_mip_gap(lprec* lp, unsigned char absolute);
WINAPI set_bb_rule(lprec* lp, int bb_rule);
WINAPI get_bb_rule(lprec* lp);
WINAPI set_var_branch(lprec* lp, int colnr, int branch_mode);
WINAPI get_var_branch(lprec* lp, int colnr);
WINAPI is_infinite(lprec* lp, double value);
WINAPI set_infinite(lprec* lp, double infinite);
WINAPI get_infinite(lprec* lp);
WINAPI set_epsint(lprec* lp, double epsint);
WINAPI get_epsint(lprec* lp);
WINAPI set_epsb(lprec* lp, double epsb);
WINAPI get_epsb(lprec* lp);
WINAPI set_epsd(lprec* lp, double epsd);
WINAPI get_epsd(lprec* lp);
WINAPI set_epsel(lprec* lp, double epsel);
WINAPI get_epsel(lprec* lp);
WINAPI set_epslevel(lprec* lp, int epslevel);
WINAPI set_scaling(lprec* lp, int scalemode);
WINAPI get_scaling(lprec* lp);
WINAPI is_scalemode(lprec* lp, int testmask);
WINAPI is_scaletype(lprec* lp, int scaletype);
WINAPI is_integerscaling(lprec* lp);
WINAPI set_scalelimit(lprec* lp, double scalelimit);
WINAPI get_scalelimit(lprec* lp);
WINAPI set_improve(lprec* lp, int improve);
WINAPI get_improve(lprec* lp);
WINAPI set_pivoting(lprec* lp, int piv_rule);
WINAPI get_pivoting(lprec* lp);
WINAPI set_partialprice(lprec* lp, int blockcount, int* blockstart, unsigned char isrow);
WINAPI get_partialprice(lprec* lp, int* blockcount, int* blockstart, unsigned char isrow);
WINAPI set_multiprice(lprec* lp, int multiblockdiv);
WINAPI get_multiprice(lprec* lp, unsigned char getabssize);
WINAPI is_use_names(lprec* lp, unsigned char isrow);
WINAPI set_use_names(lprec* lp, unsigned char isrow, unsigned char use_names);
WINAPI get_nameindex(lprec* lp, char* varname, unsigned char isrow);
WINAPI is_piv_mode(lprec* lp, int testmask);
WINAPI is_piv_rule(lprec* lp, int rule);
WINAPI set_break_at_first(lprec* lp, unsigned char break_at_first);
WINAPI is_break_at_first(lprec* lp);
WINAPI set_bb_floorfirst(lprec* lp, int bb_floorfirst);
WINAPI get_bb_floorfirst(lprec* lp);
WINAPI set_bb_depthlimit(lprec* lp, int bb_maxlevel);
WINAPI get_bb_depthlimit(lprec* lp);
WINAPI set_break_at_value(lprec* lp, double break_at_value);
WINAPI get_break_at_value(lprec* lp);
WINAPI set_negrange(lprec* lp, double negrange);
WINAPI get_negrange(lprec* lp);
WINAPI set_epsperturb(lprec* lp, double epsperturb);
WINAPI get_epsperturb(lprec* lp);
WINAPI set_epspivot(lprec* lp, double epspivot);
WINAPI get_epspivot(lprec* lp);
WINAPI get_max_level(lprec* lp);
long long WINAPI get_total_nodes(lprec* lp);
long long WINAPI get_total_iter(lprec* lp);
WINAPI get_objective(lprec* lp);
WINAPI get_working_objective(lprec* lp);
WINAPI get_var_primalresult(lprec* lp, int index);
WINAPI get_var_dualresult(lprec* lp, int index);
WINAPI get_variables(lprec* lp, double* var);
WINAPI get_ptr_variables(lprec* lp, double** var);
WINAPI get_constraints(lprec* lp, double* constr);
WINAPI get_ptr_constraints(lprec* lp, double** constr);
WINAPI get_sensitivity_rhs(lprec* lp, double* duals, double* dualsfrom, double* dualstill);
WINAPI get_ptr_sensitivity_rhs(lprec* lp, double** duals, double** dualsfrom, double** dualstill);
WINAPI get_sensitivity_obj(lprec* lp, double* objfrom, double* objtill);
WINAPI get_sensitivity_objex(lprec* lp, double* objfrom, double* objtill, double* objfromvalue, double* objtillvalue);
WINAPI get_ptr_sensitivity_obj(lprec* lp, double** objfrom, double** objtill);
WINAPI get_ptr_sensitivity_objex(lprec* lp, double** objfrom, double** objtill, double** objfromvalue, double** objtillvalue);
WINAPI set_solutionlimit(lprec* lp, int limit);
WINAPI get_solutionlimit(lprec* lp);
WINAPI get_solutioncount(lprec* lp);
WINAPI get_Norig_rows(lprec* lp);
WINAPI get_Nrows(lprec* lp);
WINAPI get_Lrows(lprec* lp);
WINAPI get_Norig_columns(lprec* lp);
WINAPI get_Ncolumns(lprec* lp);
typedef int (WINAPI)(void* userhandle, char* buf, int max_size);
typedef int (WINAPI)(void* userhandle, char* buf);
WINAPI MPS_readex(lprec** newlp, void* userhandle, read_modeldata_func read_modeldata, int typeMPS, int options);
WINAPI read_lpex(void* userhandle, read_modeldata_func read_modeldata, int verbose, char* lp_name);
WINAPI write_lpex(lprec* lp, void* userhandle, write_modeldata_func write_modeldata);
WINAPI read_mpsex(void* userhandle, read_modeldata_func read_modeldata, int options);
WINAPI read_freempsex(void* userhandle, read_modeldata_func read_modeldata, int options);
WINAPI MPS_writefileex(lprec* lp, int typeMPS, void* userhandle, write_modeldata_func write_modeldata);
/** Forward definitions of functions used internaly by the lp toolkit */
unsigned char set_callbacks(lprec* lp);
int yieldformessages(lprec* lp);
WINAPI userabort(lprec* lp, int message);
/** Memory management routines */
unsigned char append_rows(lprec* lp, int deltarows);
unsigned char append_columns(lprec* lp, int deltacolumns);
void inc_rows(lprec* lp, int delta);
void inc_columns(lprec* lp, int delta);
unsigned char init_rowcol_names(lprec* lp);
unsigned char inc_row_space(lprec* lp, int deltarows);
unsigned char inc_col_space(lprec* lp, int deltacols);
unsigned char shift_rowcoldata(lprec* lp, int base, int delta, LLrec* usedmap, unsigned char isrow);
unsigned char shift_basis(lprec* lp, int base, int delta, LLrec* usedmap, unsigned char isrow);
unsigned char shift_rowdata(lprec* lp, int base, int delta, LLrec* usedmap);
unsigned char shift_coldata(lprec* lp, int base, int delta, LLrec* usedmap);
/** INLINE */
unsigned char is_chsign(lprec* lp, int rownr);
unsigned char inc_lag_space(lprec* lp, int deltarows, unsigned char ignoreMAT);
lprec* make_lag(lprec* server);
double get_rh_upper(lprec* lp, int rownr);
double get_rh_lower(lprec* lp, int rownr);
unsigned char set_rh_upper(lprec* lp, int rownr, double value);
unsigned char set_rh_lower(lprec* lp, int rownr, double value);
int bin_count(lprec* lp, unsigned char working);
int MIP_count(lprec* lp);
int SOS_count(lprec* lp);
int GUB_count(lprec* lp);
int identify_GUB(lprec* lp, unsigned char mark);
int prepare_GUB(lprec* lp);
unsigned char refactRecent(lprec* lp);
unsigned char check_if_less(lprec* lp, double x, double y, int variable);
unsigned char feasiblePhase1(lprec* lp, double epsvalue);
void free_duals(lprec* lp);
void initialize_solution(lprec* lp, unsigned char shiftbounds);
void recompute_solution(lprec* lp, unsigned char shiftbounds);
int verify_solution(lprec* lp, unsigned char reinvert, char* info);
int check_solution(lprec* lp, int lastcolumn, double* solution, double* upbo, double* lowbo, double tolerance);
/** INLINE */
unsigned char is_fixedvar(lprec* lp, int variable);
/** INLINE */
unsigned char is_splitvar(lprec* lp, int colnr);
WINAPI set_action(int* actionvar, int actionmask);
WINAPI clear_action(int* actionvar, int actionmask);
WINAPI is_action(int actionvar, int testmask);
/** INLINE */
unsigned char is_bb_rule(lprec* lp, int bb_rule);
/** INLINE */
unsigned char is_bb_mode(lprec* lp, int bb_mask);
/** INLINE */
int get_piv_rule(lprec* lp);
char* get_str_piv_rule(int rule);
WINAPI set_var_priority(lprec* lp);
int find_sc_bbvar(lprec* lp, int* count);
int find_sos_bbvar(lprec* lp, int* count, unsigned char intsos);
int find_int_bbvar(lprec* lp, int* count, BBrec* BB, unsigned char* isfeasible);
/** Solution-related functions */
double compute_dualslacks(lprec* lp, int target, double** dvalues, int** nzdvalues, unsigned char dosum);
unsigned char solution_is_int(lprec* lp, int index, unsigned char checkfixed);
unsigned char bb_better(lprec* lp, int target, int mode);
void construct_solution(lprec* lp, double* target);
void transfer_solution_var(lprec* lp, int uservar);
unsigned char construct_duals(lprec* lp);
unsigned char construct_sensitivity_duals(lprec* lp);
unsigned char construct_sensitivity_obj(lprec* lp);
int add_GUB(lprec* lp, char* name, int priority, int count, int* sosvars);
basisrec* push_basis(lprec* lp, int* basisvar, unsigned char* isbasic, unsigned char* islower);
unsigned char compare_basis(lprec* lp);
unsigned char restore_basis(lprec* lp);
unsigned char pop_basis(lprec* lp, unsigned char restore);
unsigned char is_BasisReady(lprec* lp);
unsigned char is_slackbasis(lprec* lp);
unsigned char verify_basis(lprec* lp);
int unload_basis(lprec* lp, unsigned char restorelast);
int perturb_bounds(lprec* lp, BBrec* perturbed, unsigned char doRows, unsigned char doCols, unsigned char includeFIXED);
unsigned char validate_bounds(lprec* lp, double* upbo, double* lowbo);
unsigned char impose_bounds(lprec* lp, double* upbo, double* lowbo);
int unload_BB(lprec* lp);
double feasibilityOffset(lprec* lp, unsigned char isdual);
unsigned char isP1extra(lprec* lp);
double get_refactfrequency(lprec* lp, unsigned final char);
int findBasicFixedvar(lprec* lp, int afternr, unsigned char slacksonly);
unsigned char isBasisVarFeasible(lprec* lp, double tol, int basis_row);
unsigned char isPrimalFeasible(lprec* lp, double tol, int infeasibles, double* feasibilitygap);
unsigned char isDualFeasible(lprec* lp, double tol, int* boundflips, int infeasibles, double* feasibilitygap);
/** Main simplex driver routines */
int preprocess(lprec* lp);
void postprocess(lprec* lp);
unsigned char performiteration(lprec* lp, int rownr, int varin, double theta, unsigned char primal, unsigned char allowminit, double* prow, int* nzprow, double* pcol, int* nzpcol, int* boundswaps);
void transfer_solution_var(lprec* lp, int uservar);
void transfer_solution(lprec* lp, unsigned char dofinal);
/** Scaling utilities */
double scaled_floor(lprec* lp, int colnr, double value, double epsscale);
double scaled_ceil(lprec* lp, int colnr, double value, double epsscale);
/** Variable mapping utility routines */
void varmap_lock(lprec* lp);
void varmap_clear(lprec* lp);
unsigned char varmap_canunlock(lprec* lp);
void varmap_addconstraint(lprec* lp);
void varmap_addcolumn(lprec* lp);
void varmap_delete(lprec* lp, int base, int delta, LLrec* varmap);
void varmap_compact(lprec* lp, int prev_rows, int prev_cols);
unsigned char varmap_validate(lprec* lp, int varno);
/** STATIC MYBOOL del_varnameex(lprec *lp, hashelem **namelist, hashtable *ht, int varnr, LLrec *varmap); */
unsigned char del_varnameex(lprec* lp, hashelem** namelist, int items, hashtable* ht, int varnr, LLrec* varmap);
/** Pseudo-cost routines (internal) */
BBPSrec* init_pseudocost(lprec* lp, int pseudotype);
void free_pseudocost(lprec* lp);
double get_pseudorange(BBPSrec* pc, int mipvar, int varcode);
void update_pseudocost(BBPSrec* pc, int mipvar, int varcode, unsigned char capupper, double varsol);
double get_pseudobranchcost(BBPSrec* pc, int mipvar, unsigned char dofloor);
double get_pseudonodecost(BBPSrec* pc, int mipvar, int vartype, double varsol);
/** Matrix access and equation solving routines */
void set_OF_override(lprec* lp, double* ofVector);
void set_OF_p1extra(lprec* lp, double p1extra);
void unset_OF_p1extra(lprec* lp);
unsigned char modifyOF1(lprec* lp, int index, double* ofValue, double mult);
WINAPI get_OF_active(lprec* lp, int varnr, double mult);
unsigned char is_OF_nz(lprec* lp, int colnr);
int get_basisOF(lprec* lp, int coltarget, double crow, int colno);
WINAPI get_basiscolumn(lprec* lp, int j, int rn, double bj);
WINAPI obtain_column(lprec* lp, int varin, double* pcol, int* nzlist, int* maxabs);
int compute_theta(lprec* lp, int rownr, double* theta, int isupbound, double HarrisScalar, unsigned char primal);
/** Pivot utility routines */
int findBasisPos(lprec* lp, int notint, int* var_basic);
unsigned char check_degeneracy(lprec* lp, double* pcol, int* degencount);
